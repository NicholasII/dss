package cn.qdcares.rpc.service.dao;

import cn.qdcares.rpc.facade.model.DeptDto;
import cn.qdcares.rpc.facade.model.ResourceDto;
import cn.qdcares.rpc.facade.model.RoleDto;
import cn.qdcares.rpc.facade.model.UserDto;

import java.util.List;

/**
 * @Description:
 * @author dongqun
 * @date  2018/5/30 18:17
 * @version 1.0
 */
public interface IUserDao {

    public boolean addRole(String userId,RoleDto role);

    public boolean addRoleBat(String userId,List<Long> rids);

    public boolean addDept(String userId,DeptDto deptDto);

    public DeptDto getDept(String userId);

    public List<RoleDto> listAllRoles(String userid);

    public List<ResourceDto> listAllResources(String userid);

    public UserDto login(String username,String password);



}
