package cn.qdcares.rpc.auth.filter;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

/**
 * @program: dss-parent
 * @description: 会话管理监听器
 * @author: dongqun
 * @date: 2018/07/06 10:58
 **/
public class SystemSessionFilter implements SessionListener {

    @Override
    public void onStart(Session session) {
        System.out.println("会话创建："+session.getId());
    }

    @Override
    public void onStop(Session session) {
        System.out.println("会话停止："+session.getId());
    }

    @Override
    public void onExpiration(Session session) {
        System.out.println("会话过期："+session.getId());
    }
}
