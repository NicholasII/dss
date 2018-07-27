package cn.qdcares.rpc.auth.permission;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.util.AntPathMatcher;
import org.apache.shiro.util.PatternMatcher;

/**
 * @program: dss-parent
 * @description: url permission
 * @author: dongqun
 * @date: 2018/06/20 11:23
 **/
public class URLPermission implements Permission{

    private  String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public URLPermission(){

    }

    public URLPermission(String url) {
        this.url = url;
    }

    @Override
    public boolean implies(Permission p) {
        if (!(p instanceof URLPermission)) return false;
        URLPermission up = (URLPermission) p;
        PatternMatcher matcher = new AntPathMatcher();
        return matcher.matches(this.getUrl(),up.getUrl());
    }
}
