package cn.qdcares.rpc.facade.model;

import cn.qdcares.rpc.common.entity.BaseEntity;

import javax.persistence.*;

/**
 * @program: dss-parent
 * @description: 部门dto
 * @author: dongqun
 * @date: 2018/06/19 10:02
 **/
@Table(name = "t_dept")
@Entity
public class DeptDto extends BaseEntity{

    private long id;//部门id
    private String deptName;//部门名称
    private String deptDescribe;//部门描述
    private String urlPrefix;//接口前缀
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptDescribe() {
        return deptDescribe;
    }

    public void setDeptDescribe(String deptDescribe) {
        this.deptDescribe = deptDescribe;
    }

    public String getUrlPrefix() {
        return urlPrefix;
    }

    public void setUrlPrefix(String urlPrefix) {
        this.urlPrefix = urlPrefix;
    }
}
