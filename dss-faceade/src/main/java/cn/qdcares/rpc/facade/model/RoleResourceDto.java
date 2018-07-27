package cn.qdcares.rpc.facade.model;

import cn.qdcares.rpc.common.entity.BaseEntity;

import javax.persistence.*;

/**
 * @program: dss-parent
 * @description: 角色资源映射
 * @author: dongqun
 * @date: 2018/06/20 14:35
 **/
@Entity
@Table(name = "t_role_resource")
public class RoleResourceDto extends BaseEntity {

    private long id;
    private long resid;//资源id
    private long roleid;//角色id

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getResid() {
        return resid;
    }

    public void setResid(long resid) {
        this.resid = resid;
    }

    public long getRoleid() {
        return roleid;
    }

    public void setRoleid(long roleid) {
        this.roleid = roleid;
    }
}
