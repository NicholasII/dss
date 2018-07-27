package cn.qdcares.rpc.service.dao.impl;

import cn.qdcares.rpc.base.dao.BaseDao;
import cn.qdcares.rpc.common.page.PageBean;
import cn.qdcares.rpc.common.page.PageParam;
import cn.qdcares.rpc.common.utils.StringUtil;
import cn.qdcares.rpc.facade.model.*;
import cn.qdcares.rpc.service.dao.IUserDao;
import cn.qdcares.rpc.service.utils.ShiroKit;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * @program: dss-parent
 * @description: 用户dao
 * @author: dongqun
 * @date: 2018/05/30 18:23
 * @version 1.0
 **/
@Repository("userDao")
public class UserDao extends BaseDao<UserDto> implements IUserDao {
    @Autowired
    RoleDao roleDao;
    @Autowired
    DeptDao deptDao;
    @Autowired
    ResourceDao resourceDao;
    @Autowired
    UserRoleDao userRoleDao;
    @Autowired
    RoleResourceDao roleResourceDao;
    /**
     * @description 根据用户名称查询用户
     * @param  userName 用户名称
     * @return
     * @Author dongqun
     * @Date 2018/5/30 18:42
     */
    public UserDto findByUserName(String userName){
        Criterion criterion = Restrictions.like("userName",userName);
        return findUnique(criterion);
    }


    @Override
    public boolean addRole(String userId, RoleDto role) {
        String hql = "from UserDto where userId = ?";
        UserDto user = loadByHql(hql,userId);
        if (user!=null){
            UserRoleDto userRoleDto = new UserRoleDto();
            userRoleDto.setRid(role.getId());
            userRoleDto.setUid(user.getId());
            userRoleDto.setUserId(user.getUserId());
            userRoleDao.add(userRoleDto);
            return true;
        }
        return false;
    }

    public UserRoleDto hasRole(long uid,String userId,long rid){
        String hql = "from UserRoleDto ur where ur.uid = ? and ur.userId = ? and ur.rid =?";
        Object[] params = {uid, userId, rid};
        UserRoleDto ur = userRoleDao.loadByHql(hql,params);
        return ur;
    }

    public void deleteUser(String userId){
        System.out.println("----用户删除-----");
        UserDto userDto = loadByHql("from UserDto where userId = ?",userId);
        if (userDto != null && userDto.getId()!=null) {
            delete(userDto);
            System.out.println("用户已删除");
        }
    }

    @Override
    public boolean addRoleBat(String userId, List<Long> rids) {
        String hql = "from UserDto where userId = ?";
        UserDto user = loadByHql(hql,userId);
        if (user!=null){
            for (Long rid:rids){
                UserRoleDto userRoleDto = new UserRoleDto();
                userRoleDto.setRid(rid);
                userRoleDto.setUid(user.getId());
                userRoleDto.setUserId(user.getUserId());
                userRoleDao.add(userRoleDto);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean addDept(String userId, DeptDto deptDto) {
        deptDao.add(deptDto);
        String hql = "from UserDto where userId = ?";
        UserDto user = loadByHql(hql,userId);
        if (user!=null){
            user.setDept(deptDto);
            update(user);
            return true;
        }
        return false;
    }

    @Override
    public DeptDto getDept(String userid) {
        String hql = "select d from UserDto u ,DeptDto d where u.dept.id = d.id and u.userId = ?";
        return deptDao.loadByHql(hql,userid);
    }

    @Override
    public List<RoleDto> listAllRoles(String userid) {
        String hql = "select ur from UserRoleDto ur where ur.userId = ?";
        List<UserRoleDto> userRoles = userRoleDao.list(hql,userid);
        List<RoleDto> roles = new ArrayList<>();
        if (userRoles!=null){
            for (UserRoleDto userRoleDto:userRoles){
                hql = "from RoleDto where id= ?";
                RoleDto dto = roleDao.loadByHql(hql,userRoleDto.getRid());
                if (dto!=null){
                    roles.add(dto);
                }
            }
        }
        return roles;
    }

    public List<UserRoleDto> listAllRoles2(String userid) {
        String hql = "select ur from UserRoleDto ur where ur.userId = ?";
        List<UserRoleDto> userRoles = userRoleDao.list(hql,userid);
        return userRoles;
    }

    @Override
    public List<ResourceDto> listAllResources(String userid) {
        //String hql = "select res from UserDto u,ResourceDto res,RoleDto role,RoleResourceDto rr where u.id = role.user.id and role.id = rr.roleid and res.id = rr.resid and u.userId=?";
        String hql = "select ur from UserRoleDto ur where ur.userId = ?";
        List<UserRoleDto> userRoles = userRoleDao.list(hql,userid);
        List<RoleDto> roles = new ArrayList<>();
        if (userRoles!=null){
            for (UserRoleDto userRoleDto:userRoles){
                hql = "from RoleDto where id= ?";
                RoleDto dto = roleDao.loadByHql(hql,userRoleDto.getRid());
                if (dto!=null){
                    roles.add(dto);
                }
            }
        }
        Map<Long,RoleResourceDto> resourceMap = new HashMap<>();

        for (RoleDto roleDto:roles){
            hql= "from RoleResourceDto where roleid= ?";
            List<RoleResourceDto> roleResources = roleResourceDao.list(hql,roleDto.getId());
            for (RoleResourceDto rr: roleResources){
                resourceMap.put(rr.getResid(),rr);
            }
        }
        List<ResourceDto> resourceList = new ArrayList<>();
        Set<Long> set = resourceMap.keySet();
        Iterator<Long> it =  set.iterator();
        while (it.hasNext()){
            Long id = it.next();
            RoleResourceDto roleResourceDto = resourceMap.get(id);
            hql= "from ResourceDto where id=?";
            ResourceDto resourceDto = resourceDao.loadByHql(hql,roleResourceDto.getResid());
            if (resourceDto!=null) resourceList.add(resourceDto);
        }
        return resourceList;
    }

    @Override
    public UserDto login(String username, String password) {
        String hql = "from UserDto where userId = ?";
        String[] params = {username};
        UserDto user = loadByHql(hql,params);
        if (user==null) throw new UnknownAccountException("用户名或者密码错误");
        if (!user.getUserPwd().equals(ShiroKit.md5(password,username))) throw new IncorrectCredentialsException("密码不正确");
        if (user.getStatus()==0) throw new LockedAccountException("用户已经被锁定");
        return user;
    }
    /**
     * @Description: 根据查询条件返回分页用户
     * @author dongqun
     * @date  2018/6/22 11:01
     * @version 1.0
     */
    public PageBean<UserDto> listPage(PageParam param,UserDto userDto){

        Criterion IdCriterion = null,NameCriterion = null;
        if (!StringUtil.isEmpty(userDto.getUserId())){
            IdCriterion = Restrictions.eq("userId",userDto.getUserId());
        }
        if (!StringUtil.isEmpty(userDto.getUserName())){
            NameCriterion = Restrictions.like("userName",userDto.getUserId(), MatchMode.ANYWHERE);
        }
        //TODO 还可以继续添加过滤条件
        PageBean<UserDto> pageBean = new PageBean<>();
        List<UserDto> alllist = findByCriteria(IdCriterion,NameCriterion);
        pageBean.setTotal(alllist.size());
        List<UserDto> pagelist = findByPageCriteria(param,IdCriterion,NameCriterion);
        pageBean.setRows(pagelist);
        return  pageBean;
    }


}
