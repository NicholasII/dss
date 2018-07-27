package cn.qdcares.rpc.facade.model;

import cn.qdcares.rpc.common.entity.BaseEntity;

import javax.persistence.*;

/**
 * @program: dss-parent
 * @description: 资源dto
 * @author: dongqun
 * @date: 2018/06/19 10:01
 **/
@Entity
@Table(name = "t_resources")
public class ResourceDto extends BaseEntity {
    private long id;
    private String name;
    private String permission;
    private String url;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
