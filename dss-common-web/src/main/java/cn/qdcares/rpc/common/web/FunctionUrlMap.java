package cn.qdcares.rpc.common.web;

/**
 * @program: dss-parent
 * @description: 功能地址映射
 * @author: dongqun
 * @date: 2018/06/27 14:04
 **/
public class FunctionUrlMap {
    
    private int id;
    private int pid;
    private String name;
    private String url;
    private boolean open;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

}
