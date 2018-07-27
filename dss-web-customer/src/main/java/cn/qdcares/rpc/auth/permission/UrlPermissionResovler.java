package cn.qdcares.rpc.auth.permission;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.PermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;

/**
 * @program: dss-parent
 * @description: url许可解析器
 * @author: dongqun
 * @date: 2018/06/20 11:27
 **/
public class UrlPermissionResovler implements PermissionResolver {
    @Override
    public Permission resolvePermission(String s) {
        if (s.startsWith("/"))
            return new URLPermission(s);

        return new WildcardPermission(s);
    }
}
