package cn.qdcares.rpc.service.dao;

import cn.qdcares.rpc.common.page.PageBean;
import cn.qdcares.rpc.common.page.PageParam;
import cn.qdcares.rpc.common.utils.StringUtil;
import cn.qdcares.rpc.facade.model.ResourceDto;
import cn.qdcares.rpc.facade.model.RoleDto;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public interface IResourceDao {

    public PageBean<ResourceDto> findResourceByPage(PageParam param, ResourceDto resourceDto);

    public List<ResourceDto> findAllResources(ResourceDto resourceDto);
}
