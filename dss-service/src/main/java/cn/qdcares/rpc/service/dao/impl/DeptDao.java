package cn.qdcares.rpc.service.dao.impl;

import cn.qdcares.rpc.base.dao.BaseDao;
import cn.qdcares.rpc.facade.model.DeptDto;
import cn.qdcares.rpc.service.dao.IDeptDao;
import org.springframework.stereotype.Repository;

/**
 * @program: dss-parent
 * @description:
 * @author: dongqun
 * @date: 2018/06/19 15:28
 **/
@Repository("deptDao")
public class DeptDao extends BaseDao<DeptDto> implements IDeptDao {
}
