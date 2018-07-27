package cn.qdcares.rpc.service.biz;

import cn.qdcares.rpc.common.page.PageBean;
import cn.qdcares.rpc.common.page.PageParam;
import cn.qdcares.rpc.common.utils.StringUtil;
import cn.qdcares.rpc.facade.model.*;
import cn.qdcares.rpc.service.dao.impl.UserDao;
import cn.qdcares.rpc.service.dao.impl.UserRoleDao;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: dss-parent
 * @description: 用户业务处理
 * @author: dongqun
 * @date: 2018/05/30 18:29
 **/
@Service("userBiz")
public class UserBiz {
    @Autowired
    UserDao userDao;
    @Autowired
    UserRoleDao userRoleDao;

    /**
     * 添加用户，如有角色则添加.
     *
     * @param userDto
     */
    public UserDto create(UserDto userDto,List<Long> rids) {
        String password = userDto.getUserPwd();
        password = new Md5Hash(password,userDto.getUserId()).toHex();
        userDto.setUserPwd(password);
        UserDto user = userDao.add(userDto);
        if (rids!=null) userDao.addRoleBat(user.getUserId(),rids);
        return user;
    }

    /**
     * 根据ID获取用户信息.
     *
     * @param userId
     * @return
     */
    public UserDto getById(String userId) {
        /*Criterion criterion = Restrictions.eq("userId",userId);
        return userDao.findUnique(criterion);*/

        String hql = "from UserDto where userId = ?";
        return  userDao.loadByHql(hql,userId);
    }

    /**
     * 根据登录名取得用户对象
     */
    public UserDto findUserByUserName(String userName) {
        return userDao.findByUserName(userName);
    }

    /**
     * 根据ID删除一个用户，同时删除与该用户关联的角色关联信息. type="1"的超级管理员不能删除.
     *
     * @param userId 用户ID.
     */
    public void deleteUserById(String userId) {
        userDao.deleteUser(userId);
    }


    /**
     * 更新用户信息.
     *
     * @param user
     */
    public void updateUser(UserDto user,List<Long> rids) {
        userDao.update(user);
        List<UserRoleDto> havingRoles = userDao.listAllRoles2(user.getUserId());
        for (Long rid:rids){
            UserRoleDto ur = userDao.hasRole(user.getId(),user.getUserId(),rid);
            long urid = ur.getRid();
            if(ur!=null && urid!=0){
                userRoleDao.delete(urid);
            }else {
                UserRoleDto userRoleDto = new UserRoleDto();
                userRoleDto.setUserId(user.getUserId());
                userRoleDto.setUid(user.getId());
                userRoleDto.setRid(rid);
                userRoleDao.add(userRoleDto);
            }
        }
    }
    /**
     * @Description: 更新用户角色
     * @author dongqun
     * @date  2018/7/4 22:19
     * @version 1.0
     */
    private void updateUserRoles(UserDto user,List<Long> rids){

    }

    /**
     * 根据用户ID更新用户密码.
     *
     * @param userId
     * @param newPwd (已进行SHA1加密)
     */
    public void updateUserPwd(String userId, String newPwd, boolean isTrue) {
        UserDto UserDto = userDao.loadByHql("from UserDto where userId = ?",userId);
        UserDto.setUserPwd(newPwd);
        userDao.update(UserDto);
    }


    /**
     * 查询并分页列出用户信息.
     *
     * @param pageParam
     * @param hql 查询sql
     * @return
     */
    public List<UserDto> listPage(PageParam pageParam, String hql) {
        return userDao.listPage(pageParam, hql);
    }
    /**
     * @description 查询用户属于的部门
     * @param userid 用户id
     * @return
     * @Author dongqun
     * @Date 2018/6/19 15:03
     */
    public DeptDto getDept(String userid){
        return userDao.getDept(userid);
    }
    /**
     * @description 查询用户拥有的角色
     * @param
     * @return
     * @Author dongqun
     * @Date 2018/6/19 15:04
     */
    public List<RoleDto> listAllRoles(String userid){
        return userDao.listAllRoles(userid);
    }

    public List<ResourceDto> listAllResources(String userid){
        return userDao.listAllResources(userid);
    }

    public boolean addRole(String userid,RoleDto role){
        return userDao.addRole(userid,role);
    }

    public boolean addRoles(String userid,List<Long> roles){
        return userDao.addRoleBat(userid,roles);
    }

    public boolean addDept(String userid,DeptDto dept){
        return userDao.addDept(userid,dept);
    }

    public UserDto login(String username,String password){
        return  userDao.login(username,password);
    }

    public PageBean<UserDto> userList(UserDto userDto, int currpage, int pageSize){
        PageParam pageParam = new PageParam(currpage,pageSize);
        return userDao.listPage(pageParam,userDto);
    }

}
