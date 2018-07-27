package cn.qdcares.rpc.facade.service;

import cn.qdcares.rpc.common.page.PageBean;
import cn.qdcares.rpc.facade.model.DeptDto;
import cn.qdcares.rpc.facade.model.ResourceDto;
import cn.qdcares.rpc.facade.model.RoleDto;
import cn.qdcares.rpc.facade.model.UserDto;

import javax.management.relation.Role;
import java.util.List;


/**
 *  @Description: 用户Dubbo服务接口
 *  @author dongqun
 *  @date 2018/5/30 17:24
 *  @version 1.0
 */
public interface UserServiceFacade {

    public String sayHello(String name);
    /**
     * 保存用户信息.
     *
     * @param userDto
     */
    public UserDto create(UserDto userDto,List<Long> rids);

    /**
     * 根据用户ID获取用户信息.
     *
     * @param userId
     * @return
     */
    public UserDto getById(String userId);

    /**
     * 根据登录名取得用户对象
     */
    public UserDto findUserByUserName(String userName);

    /**
     * 根据ID删除一个用户，同时删除与该用户关联的角色关联信息. type="1"的超级管理员不能删除.
     * @param userId 用户ID.
     */
    public void deleteUserById(String userId);


    /**
     * 更新用户信息.
     *
     * @param user
     */
    public void update(UserDto user,List<Long> rids);

    /**
     * 根据用户ID更新用户密码.
     *
     * @param userId
     * @param newPwd (已进行SHA1加密)
     */
    public void updateUserPwd(String userId, String newPwd, boolean isTrue);



    /** 
     * @description 添加角色 
     * @param  
     * @return  
     * @Author dongqun
     * @Date 2018/6/19 17:04
     */ 
    public boolean addRole(String userid, RoleDto role);

    /** 
     * @description 添加部门 
     * @param  
     * @return  
     * @Author dongqun
     * @Date 2018/6/19 17:05
     */ 
    public boolean addDept(String userid, DeptDto dept);
    
    /** 
     * @description 获取部门 
     * @param  
     * @return  
     * @Author dongqun
     * @Date 2018/6/19 17:06
     */ 
    public DeptDto getDept(String userid);
    
    /** 
     * @description 获取全部角色 
     * @param
     * @return  
     * @Author dongqun
     * @Date 2018/6/19 17:09
     */ 
    public List<RoleDto> listAllRoles(String userid);
    
    /** 
     * @description 获取全部资源 
     * @param
     * @return  
     * @Author dongqun
     * @Date 2018/6/20 14:45
     */ 
    public List<ResourceDto> listAllResources(String userid);
    
    /** 
     * @description 登录返回用户 
     * @param password 密码已加密 
     * @return
     * @Author dongqun
     * @Date 2018/6/19 17:55
     */ 
    public UserDto login(String username,String password);

    /**
     * @Description: 分页获取用户列表--可加条件过滤
     * @author dongqun
     * @date  2018/6/22 10:22
     * @version
     */
    public PageBean<UserDto> userPageList(UserDto userDto, int currpage, int pageSize);
    
    
}
