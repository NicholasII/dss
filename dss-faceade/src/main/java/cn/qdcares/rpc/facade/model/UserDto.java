package cn.qdcares.rpc.facade.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.qdcares.rpc.common.entity.BaseEntity;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;


/**
 *  @Description:
 *  @author dongqun
 *  @date 2018/5/30 17:18
 */
@Entity
@Table(name = "t_user")
public class UserDto extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long id;//主键
    @Column(name = "userId")
    private String userId;// 用户ID
    @Column(name = "userName")
    private String userName;// 用户ID
    @Column(name = "userPwd")
    private String userPwd; // 登录密码
    @Column(name = "phone")
    private String phone; // 手机号
    @Column(name = "email")
    private String email;// 邮箱
    @Column(name = "status")
    private Integer status; // 状态 1:可用，0:不可用
    @Column(name = "lastLoginTime")
    private Date lastLoginTime;// 最后登录时间
    @Column(name = "remark")
    private String remark; // 描述

    //private List<RoleDto> roleDtos;//用户拥有的角色

    private DeptDto dept;//用户所在的部门


    /**
     * 主键ID
     **/
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    /**
     * 主键ID
     **/
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * 登录密码
     *
     * @return
     */
    public String getUserPwd() {
        return userPwd;
    }

    /**
     * 登录密码
     *
     * @return
     */
    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    /**
     * 邮箱
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * 邮箱
     *
     * @return
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 状态(100:可用，101:不可用 )
     *
     * @return
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 用户id
     *
     * @return
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 用户id
     *
     * @return
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 手机
     *
     * @return
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 手机
     *
     * @return
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**

     * 状态(100:可用，101:不可用 )
     *
     * @return
     */
    public void setStatus(Integer status) {
        this.status = status;
    }


    /**
     * 最后登录时间
     *
     * @return
     */
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    /**
     * 最后登录时间
     *
     * @return
     */
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    /**
     * @return
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 描述
     *
     * @return
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }


    /*public UserDto() {
        roleDtos = new ArrayList<>();
    }

    public void  addRole(RoleDto roleDto){
        roleDtos.add(roleDto);
    }

    @OneToMany(mappedBy = "user",cascade = CascadeType.MERGE, fetch = FetchType.LAZY ,)
    @LazyCollection(LazyCollectionOption.EXTRA)
    public List<RoleDto> getRoleDtos() {
        return roleDtos;
    }

    public void setRoleDtos(List<RoleDto> roleDtos) {
        this.roleDtos = roleDtos;
    }*/

    @ManyToOne
    @JoinColumn(name = "deptId")
    public DeptDto getDept() {
        return dept;
    }

    public void setDept(DeptDto dept) {
        this.dept = dept;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
