package cn.qdcares.rpc.service.dao.impl;

import cn.qdcares.rpc.base.dao.BaseDao;
import cn.qdcares.rpc.facade.model.ResourceDto;
import cn.qdcares.rpc.facade.model.RoleDto;
import cn.qdcares.rpc.facade.model.RoleResourceDto;
import cn.qdcares.rpc.service.dao.IRoleResourceDao;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: dss-parent
 * @description:
 * @author: dongqun
 * @date: 2018/06/20 15:00
 **/
@Repository
public class RoleResourceDao extends BaseDao<RoleResourceDto> implements IRoleResourceDao{


    @Override
    public void unAuthResource(RoleDto role, ResourceDto resource) {
        long rid = role.getId();
        long resid = resource.getId();
        String hql = "delete RoleResourceDto rr where rr.resid = ? and rr.roleid = ?";
        Object[] objs = {resid,rid};
        Query query = setQuery(hql,objs);
        query.executeUpdate();
    }

    @Override
    public void unAuthResourceBat(RoleDto role, List<ResourceDto> resources) {
        for (ResourceDto resource:resources){
            unAuthResource(role,resource);
        }
    }

    @Override
    public List<RoleResourceDto> hasResources(long roleid) {
        String hql = "from RoleResourceDto rr where rr.roleid = ?";
        List<RoleResourceDto> rrlist = list(hql,roleid);
        return rrlist;
    }
}
