package cn.qdcares.rpc.facade.model;

import cn.qdcares.rpc.common.entity.BaseEntity;
import cn.qdcares.rpc.facade.enumerate.RoleType;

import javax.persistence.*;
import java.util.Date;

/**
 * @program: dss-parent
 * @description: 角色
 * @author: dongqun
 * @date: 2018/06/19 10:02
 **/
@Entity
@Table(name = "t_role")
public class RoleDto extends BaseEntity {
    private long id;
    private String roleName;
    private RoleType roleType;
    private String roleDesc;
    private Date updateTime;
    private Date createTime;
    //private UserDto user;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    @Column
    @Enumerated(EnumType.STRING)
    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /*@ManyToOne(cascade = CascadeType.ALL,optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "uesrId")
    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }*/

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
