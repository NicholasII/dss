package cn.qdcares.rpc.facade.service.impl;

import cn.qdcares.rpc.common.page.PageBean;
import cn.qdcares.rpc.facade.model.DeptDto;
import cn.qdcares.rpc.facade.model.ResourceDto;
import cn.qdcares.rpc.facade.model.RoleDto;
import cn.qdcares.rpc.facade.model.UserDto;
import cn.qdcares.rpc.facade.service.UserServiceFacade;
import cn.qdcares.rpc.service.biz.UserBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: dss-parent
 * @description: 用户Dubbo服务接口实现
 * @author: dongqun
 * @date: 2018/05/30 18:45
 * @version 1.0
 **/
@Service("userService")
public class UserServiceImpl implements UserServiceFacade{

    @Autowired
    UserBiz userBiz;

    @Override
    public String sayHello(String name) {
        return String.format("Hi,Client_%s",name);
    }

    public UserDto create(UserDto userDto,List<Long> rids) {
        return userBiz.create(userDto,rids);
    }

    public UserDto getById(String userId) {
        UserDto userDto = userBiz.getById(userId);
        return userDto;
    }

    public UserDto findUserByUserName(String userName) {
        return userBiz.findUserByUserName(userName);
    }

    public void deleteUserById(String userId) {
        userBiz.deleteUserById(userId);
    }

    @Override
    public void update(UserDto user,List<Long> rids) {
        userBiz.updateUser(user,rids);
    }

    public void updateUserPwd(String userId, String newPwd, boolean isTrue) {
        userBiz.updateUserPwd(userId,newPwd,isTrue);
    }

    @Override
    public boolean addRole(String userid, RoleDto role) {
        return userBiz.addRole(userid,role);
    }

    @Override
    public boolean addDept(String userid, DeptDto dept) {
        return userBiz.addDept(userid,dept);
    }

    @Override
    public DeptDto getDept(String userid) {
        return userBiz.getDept(userid);
    }

    @Override
    public List<RoleDto> listAllRoles(String userid) {
        return userBiz.listAllRoles(userid);
    }

    @Override
    public List<ResourceDto> listAllResources(String userid) {
        return userBiz.listAllResources(userid);
    }

    @Override
    public UserDto login(String username, String password) {
        return userBiz.login(username,password);
    }

    @Override
    public PageBean<UserDto> userPageList(UserDto userDto, int currpage, int pageSize) {
        return userBiz.userList(userDto,  currpage,  pageSize);
    }
}
