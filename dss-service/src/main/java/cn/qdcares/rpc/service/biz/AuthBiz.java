package cn.qdcares.rpc.service.biz;

import cn.qdcares.rpc.facade.model.ResourceDto;
import cn.qdcares.rpc.facade.model.RoleDto;
import cn.qdcares.rpc.facade.model.RoleResourceDto;
import cn.qdcares.rpc.service.dao.impl.RoleResourceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: dss-parent
 * @description:
 * @author: dongqun
 * @date: 2018/06/25 10:51
 **/
@Service
public class AuthBiz {
    @Autowired
    RoleResourceDao rrDao;


    public RoleResourceDto authResource(RoleDto role, ResourceDto resource) {
        RoleResourceDto rr = new RoleResourceDto();
        rr.setResid(resource.getId());
        rr.setRoleid(role.getId());
        return rrDao.add(rr);
    }


    public List<RoleResourceDto> authResourceBat(RoleDto role, List<ResourceDto> resources) {
        List<RoleResourceDto> rrlist  = new ArrayList<>();
        for (ResourceDto resource:resources){
            RoleResourceDto rr = new RoleResourceDto();
            rr.setResid(resource.getId());
            rr.setRoleid(role.getId());
            rrDao.add(rr);
            rrlist.add(rr);
        }
        return rrlist;
    }


    public void unAuthResource(RoleDto role, ResourceDto resource) {
        rrDao.unAuthResource(role,resource);
    }


    public void unAuthResourceBat(RoleDto role, List<ResourceDto> resources) {
        rrDao.unAuthResourceBat(role,resources);
    }

    public List<RoleResourceDto> hasResources(long roleid){
        return rrDao.hasResources(roleid);
    }
}
