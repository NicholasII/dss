package cn.qdcares.rpc.service.dao.impl;

import cn.qdcares.rpc.base.dao.BaseDao;
import cn.qdcares.rpc.common.page.PageBean;
import cn.qdcares.rpc.common.page.PageParam;
import cn.qdcares.rpc.common.utils.StringUtil;
import cn.qdcares.rpc.facade.model.ResourceDto;
import cn.qdcares.rpc.facade.model.RoleDto;
import cn.qdcares.rpc.facade.model.RoleResourceDto;
import cn.qdcares.rpc.service.dao.IRoleDao;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: dss-parent
 * @description:
 * @author: dongqun
 * @date: 2018/06/19 15:26
 **/
@Repository("roleDao")
public class RoleDao extends BaseDao<RoleDto> implements IRoleDao  {
    @Autowired
    ResourceDao resourceDao;
    @Autowired
    RoleResourceDao roleResourceDao;
    @Override
    public void addResource(long rid, ResourceDto resource) {
        resourceDao.add(resource);
        RoleResourceDto roleResourceDto = new RoleResourceDto();
        roleResourceDto.setResid(resource.getId());
        roleResourceDto.setRoleid(rid);
        roleResourceDao.add(roleResourceDto);
    }

    @Override
    public void addResources(long rid,List<ResourceDto> resources) {
        for (ResourceDto resourceDto:resources){
            addResource(rid,resourceDto);
        }
    }

    @Override
    public List<ResourceDto> listAllResources(long rid) {
        String hql = "select res from RoleDto r,ResourceDto res,RoleResourceDto rr" +
                "where r.id=rr.roleid and res.id = rr.resid and r.id=?";
        return resourceDao.list(hql,rid);
    }

    @Override
    public PageBean<RoleDto> findRoleByPage(PageParam param, RoleDto role) {
        Criterion idCriterion = null,nameCriterion = null;
        if (role.getId()!=0){
            idCriterion = Restrictions.eq("id",role.getId());
        }
        if (!StringUtil.isEmpty(role.getRoleName())){
            nameCriterion = Restrictions.eq("roleName",role.getRoleName());
        }
        PageBean<RoleDto> pageBean = new PageBean();
        List<RoleDto> allList = findByCriteria(idCriterion,nameCriterion);
        if (allList!=null){
            pageBean.setTotal(allList.size());
        }else {
            pageBean.setTotal(0);
        }
        List<RoleDto> pageList = findByPageCriteria(param,idCriterion,nameCriterion);
        pageBean.setRows(pageList);
        return pageBean;
    }

    @Override
    public List<RoleDto> findAllRoles(RoleDto role) {
        Criterion idCriterion = null,nameCriterion = null;
        if (role.getId()!=0){
            idCriterion = Restrictions.eq("id",role.getId());
        }
        if (!StringUtil.isEmpty(role.getRoleName())){
            nameCriterion = Restrictions.eq("roleName",role.getRoleName());
        }
        return findByCriteria(idCriterion,nameCriterion);
    }
}
