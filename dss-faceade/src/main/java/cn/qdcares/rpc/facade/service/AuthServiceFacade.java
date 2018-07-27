package cn.qdcares.rpc.facade.service;

import cn.qdcares.rpc.common.page.PageBean;
import cn.qdcares.rpc.common.page.PageParam;
import cn.qdcares.rpc.facade.model.ResourceDto;
import cn.qdcares.rpc.facade.model.RoleDto;
import cn.qdcares.rpc.facade.model.RoleResourceDto;

import javax.management.relation.Role;
import java.util.List;

/**
 * @Description: 授权相关服务的接口
 * @author dongqun
 * @date  2018/6/25 9:24
 * @version 1.0
 */
public interface AuthServiceFacade {

    public RoleDto addRole(RoleDto role);

    public void deleteRole(RoleDto role);

    public RoleDto findRole(RoleDto role);

    public RoleDto update(RoleDto role);

    public ResourceDto addResource(ResourceDto resource);

    public void deleteResource(ResourceDto resource);

    public ResourceDto findResource(ResourceDto resource);

    public ResourceDto updateResource(ResourceDto resource);

    public RoleResourceDto authResource(RoleDto role,ResourceDto resource);

    public List<RoleResourceDto> authResourceBat(RoleDto role,List<ResourceDto> resources);

    public void unAuthResource(RoleDto role,ResourceDto resource);

    public void unAuthResourceBat(RoleDto role,List<ResourceDto> resources);

    public PageBean<RoleDto> findRoleByPage(PageParam param, RoleDto role);

    public List<RoleDto> findAllRoles(RoleDto role);

    public PageBean<ResourceDto> findResourceByPage(PageParam param, ResourceDto resourceDto);

    public List<ResourceDto> findAllResources(ResourceDto resourceDto);

    public List<RoleResourceDto> hasResources(long roleid);
}
