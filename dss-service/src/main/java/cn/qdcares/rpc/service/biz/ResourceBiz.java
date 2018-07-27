package cn.qdcares.rpc.service.biz;

import cn.qdcares.rpc.common.page.PageBean;
import cn.qdcares.rpc.common.page.PageParam;
import cn.qdcares.rpc.common.utils.StringUtil;
import cn.qdcares.rpc.facade.model.ResourceDto;
import cn.qdcares.rpc.facade.model.RoleDto;
import cn.qdcares.rpc.service.dao.impl.ResourceDao;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: dss-parent
 * @description:
 * @author: dongqun
 * @date: 2018/06/25 10:51
 **/
@Service
public class ResourceBiz {
    @Autowired
    ResourceDao resourceDao;

    public ResourceDto addResource(ResourceDto resource) {
        return resourceDao.add(resource);
    }


    public void deleteResource(ResourceDto resource) {
        resourceDao.delete(resource);
    }


    public ResourceDto findResource(ResourceDto resource) {
        Criterion idCriterion = null,nameCriterion = null;
        if (resource.getId()!=0){
            idCriterion = Restrictions.eq("id",resource.getId());
        }
        if (StringUtil.isEmpty(resource.getName())){
            nameCriterion = Restrictions.eq("name",resource.getName());
        }
        return resourceDao.findUnique(idCriterion,nameCriterion);
    }


    public ResourceDto updateResource(ResourceDto resource) {
        resourceDao.update(resource);
        return resource;
    }

    public PageBean<ResourceDto> findRoleByPage(PageParam param, ResourceDto resourceDto) {
        return resourceDao.findResourceByPage(param ,resourceDto);
    }

    public List<ResourceDto> findAllRespurces(ResourceDto resourceDto) {
        return resourceDao.findAllResources(resourceDto);
    }

}
