package cn.qdcares.rpc.common.entity;

import java.io.Serializable;
import java.util.Date;

/**
 *  @Description: 基础实体类，包含各实体公用属性
 *  @author dongqun
 *  @date 2018/5/30 17:20
 *  @version 1.0
 */
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 版本号
     **/
    private Integer version = 0;

    /**
     * 创建时间
     **/
    private Date createTime;


    /**
     * 版本号
     **/
    public Integer getVersion() {
        return version;
    }

    /**
     * 版本号
     **/
    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     * 创建时间
     **/
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     **/
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
