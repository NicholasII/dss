package cn.qdcares.rpc.service.dao.impl;

import cn.qdcares.rpc.base.dao.BaseDao;
import cn.qdcares.rpc.common.page.PageBean;
import cn.qdcares.rpc.common.page.PageParam;
import cn.qdcares.rpc.common.utils.StringUtil;
import cn.qdcares.rpc.facade.model.ResourceDto;
import cn.qdcares.rpc.facade.model.RoleDto;
import cn.qdcares.rpc.service.dao.IResourceDao;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: dss-parent
 * @description: 资源dao
 * @author: dongqun
 * @date: 2018/06/19 16:41
 **/
@Repository("resourcesDao")
public class ResourceDao extends BaseDao<ResourceDto> implements IResourceDao {

    @Override
    public PageBean<ResourceDto> findResourceByPage(PageParam param, ResourceDto resourceDto) {
        Criterion idCriterion = null,nameCriterion = null;
        if (resourceDto.getId()!=0){
            idCriterion = Restrictions.eq("id",resourceDto.getId());
        }
        if (!StringUtil.isEmpty(resourceDto.getName())){
            nameCriterion = Restrictions.eq("name",resourceDto.getName());
        }
        PageBean<ResourceDto> pageBean = new PageBean();
        List<ResourceDto> allList = findByCriteria(idCriterion,nameCriterion);
        if (allList!=null){
            pageBean.setTotal(allList.size());
        }else {
            pageBean.setTotal(0);
        }
        List<ResourceDto> pageList = findByPageCriteria(param,idCriterion,nameCriterion);
        pageBean.setRows(pageList);
        return pageBean;
    }

    @Override
    public List<ResourceDto> findAllResources(ResourceDto resourceDto) {
        Criterion idCriterion = null,nameCriterion = null;
        if (resourceDto.getId()!=0){
            idCriterion = Restrictions.eq("id",resourceDto.getId());
        }
        if (StringUtil.isEmpty(resourceDto.getName())){
            nameCriterion = Restrictions.eq("name",resourceDto.getName());
        }
        return findByCriteria(idCriterion,nameCriterion);
    }
}
