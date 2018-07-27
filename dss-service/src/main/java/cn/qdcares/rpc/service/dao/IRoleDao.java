package cn.qdcares.rpc.service.dao;

import cn.qdcares.rpc.common.page.PageBean;
import cn.qdcares.rpc.common.page.PageParam;
import cn.qdcares.rpc.facade.model.ResourceDto;
import cn.qdcares.rpc.facade.model.RoleDto;

import java.util.List;

public interface IRoleDao {

    public void addResource(long rid,ResourceDto resource);

    public void addResources(long rid,List<ResourceDto> resources);

    public List<ResourceDto> listAllResources(long rid);

    public PageBean<RoleDto> findRoleByPage(PageParam param, RoleDto role);

    public List<RoleDto> findAllRoles(RoleDto role);
}
