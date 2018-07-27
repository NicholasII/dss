package cn.qdcares.rpc.service.dao;

import cn.qdcares.rpc.facade.model.ResourceDto;
import cn.qdcares.rpc.facade.model.RoleDto;
import cn.qdcares.rpc.facade.model.RoleResourceDto;

import java.util.List;

public interface IRoleResourceDao {

    public void unAuthResource(RoleDto role, ResourceDto resource);

    public void unAuthResourceBat(RoleDto role, List<ResourceDto> resources);

    public List<RoleResourceDto> hasResources(long roleid);
}
