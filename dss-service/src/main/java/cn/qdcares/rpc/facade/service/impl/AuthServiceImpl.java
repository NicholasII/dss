package cn.qdcares.rpc.facade.service.impl;

import cn.qdcares.rpc.common.page.PageBean;
import cn.qdcares.rpc.common.page.PageParam;
import cn.qdcares.rpc.facade.model.ResourceDto;
import cn.qdcares.rpc.facade.model.RoleDto;
import cn.qdcares.rpc.facade.model.RoleResourceDto;
import cn.qdcares.rpc.facade.service.AuthServiceFacade;
import cn.qdcares.rpc.service.biz.AuthBiz;
import cn.qdcares.rpc.service.biz.ResourceBiz;
import cn.qdcares.rpc.service.biz.RoleBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: dss-parent
 * @description: 授权
 * @author: dongqun
 * @date: 2018/06/25 10:48
 **/
@Service("authService")
public class AuthServiceImpl implements AuthServiceFacade{

    @Autowired
    RoleBiz roleBiz;
    @Autowired
    ResourceBiz resourceBiz;
    @Autowired
    AuthBiz authBiz;


    @Override
    public RoleDto addRole(RoleDto role) {
        return roleBiz.addRole(role);
    }

    @Override
    public void deleteRole(RoleDto role) {
        roleBiz.deleteRole(role);
    }

    @Override
    public RoleDto findRole(RoleDto role) {
        return roleBiz.findRole(role);
    }

    @Override
    public RoleDto update(RoleDto role) {
        return roleBiz.update(role);
    }

    @Override
    public ResourceDto addResource(ResourceDto resource) {
        return resourceBiz.addResource(resource);
    }

    @Override
    public void deleteResource(ResourceDto resource) {
        resourceBiz.deleteResource(resource);
    }

    @Override
    public ResourceDto findResource(ResourceDto resource) {
        return resourceBiz.findResource(resource);
    }

    @Override
    public ResourceDto updateResource(ResourceDto resource) {
        return resourceBiz.updateResource(resource);
    }

    @Override
    public RoleResourceDto authResource(RoleDto role, ResourceDto resource) {
        return authBiz.authResource(role,resource);
    }

    @Override
    public List<RoleResourceDto> authResourceBat(RoleDto role, List<ResourceDto> resources) {
        return authBiz.authResourceBat(role,resources);
    }

    @Override
    public void unAuthResource(RoleDto role, ResourceDto resource) {
        authBiz.unAuthResource(role,resource);
    }

    @Override
    public void unAuthResourceBat(RoleDto role, List<ResourceDto> resources) {
        authBiz.unAuthResourceBat(role,resources);
    }


    public PageBean<RoleDto> findRoleByPage(PageParam param, RoleDto role) {
        return roleBiz.findRoleByPage(param ,role);
    }

    public List<RoleDto> findAllRoles(RoleDto role) {
        return roleBiz.findAllRoles(role);
    }

    @Override
    public PageBean<ResourceDto> findResourceByPage(PageParam param, ResourceDto resourceDto) {
        return resourceBiz.findRoleByPage(param,resourceDto);
    }

    @Override
    public List<ResourceDto> findAllResources(ResourceDto resourceDto) {
        return resourceBiz.findAllRespurces(resourceDto);
    }

    @Override
    public List<RoleResourceDto> hasResources(long roleid) {
        return authBiz.hasResources(roleid);
    }

}
