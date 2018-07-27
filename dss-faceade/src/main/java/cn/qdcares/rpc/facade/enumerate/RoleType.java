package cn.qdcares.rpc.facade.enumerate;

public enum RoleType {

    vister("普通旅客"),vip_vister("vip旅客"),staff("普通员工"),dept_head("部门领导"),leader("机场领导");

    private String type;

    RoleType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
