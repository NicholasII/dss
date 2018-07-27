package cn.qdcares.rpc.service.biz;

import cn.qdcares.rpc.common.page.PageBean;
import cn.qdcares.rpc.common.page.PageParam;
import cn.qdcares.rpc.common.utils.StringUtil;
import cn.qdcares.rpc.facade.model.RoleDto;
import cn.qdcares.rpc.service.dao.impl.RoleDao;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: dss-parent
 * @description:
 * @author: dongqun
 * @date: 2018/06/25 10:49
 **/
@Service
public class RoleBiz {
    @Autowired
    RoleDao roleDao;


    public RoleDto addRole(RoleDto role) {
        return roleDao.add(role);
    }


    public void deleteRole(RoleDto role) {
        roleDao.delete(role);
        return;
    }


    public RoleDto findRole(RoleDto role) {
        Criterion idCriterion = null,nameCriterion = null;
        if (role.getId()!=0){
            idCriterion = Restrictions.eq("id",role.getId());
        }
        if (StringUtil.isEmpty(role.getRoleName())){
            nameCriterion = Restrictions.eq("roleName",role.getRoleName());
        }
        return roleDao.findUnique(idCriterion,nameCriterion);
    }

    public PageBean<RoleDto> findRoleByPage(PageParam param, RoleDto role) {
        return roleDao.findRoleByPage(param ,role);
    }

    public List<RoleDto> findAllRoles(RoleDto role) {
        return roleDao.findAllRoles(role);
    }


    public RoleDto update(RoleDto role) {
        roleDao.update(role);
        return role;
    }
}
