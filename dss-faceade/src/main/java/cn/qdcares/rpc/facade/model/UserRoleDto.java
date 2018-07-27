package cn.qdcares.rpc.facade.model;

import cn.qdcares.rpc.common.entity.BaseEntity;

import javax.persistence.*;

/**
 * @program: dss-parent
 * @description: 用户角色映射
 * @author: dongqun
 * @date: 2018/06/21 11:24
 **/
@Entity
@Table(name = "t_user_role")
public class UserRoleDto extends BaseEntity {

    private long id;
    private long uid;
    private String userId;
    private long rid;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getRid() {
        return rid;
    }

    public void setRid(long rid) {
        this.rid = rid;
    }
}
