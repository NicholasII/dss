package cn.qdcares.rpc.auth;

import cn.qdcares.rpc.facade.model.ResourceDto;
import cn.qdcares.rpc.facade.model.RoleDto;
import cn.qdcares.rpc.facade.model.UserDto;
import cn.qdcares.rpc.facade.service.UserServiceFacade;
import cn.qdcares.rpc.web.InitServlet;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @program: dss-parent
 * @description: shiro认证和授权realm
 * @author: dongqun
 * @date: 2018/06/19 17:40
 **/
public class AuthRealm extends AuthorizingRealm {

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //TODO 查询登录账号所拥有的角色和资源访问权限
        Object object = principalCollection.getPrimaryPrincipal();
        System.out.println(object);

        //UserDto user = (UserDto) principalCollection.getPrimaryPrincipal();
        String userId = (String) object;
        UserServiceFacade userServiceFacade = (UserServiceFacade) InitServlet.getBean("userService");
        List<RoleDto> roles = userServiceFacade.listAllRoles(userId);
        List<ResourceDto> resources = userServiceFacade.listAllResources(userId);
        List<String> str_roles = new ArrayList<>();
        List<String> permissions = new ArrayList<>();
        for(RoleDto role:roles){
            str_roles.add(role.getRoleName());
        }
        for (ResourceDto resource:resources){
            permissions.add(resource.getUrl());
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(new HashSet<String>(str_roles));
        info.setStringPermissions(new HashSet<>(permissions));
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        UserServiceFacade userService = (UserServiceFacade) InitServlet.getBean("userService");
        String username = token.getPrincipal().toString();
        String password = new String((char[])token.getCredentials());
        UserDto userDto = userService.login(username,password);
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username,userDto.getUserPwd(),this.getName());
        info.setCredentialsSalt(ByteSource.Util.bytes(userDto.getUserId()));
        return info;
    }
}
