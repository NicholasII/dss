package cn.qdcares.rpc.auth.test;

import cn.qdcares.rpc.common.page.PageParam;
import cn.qdcares.rpc.facade.enumerate.RoleType;
import cn.qdcares.rpc.facade.model.*;
import cn.qdcares.rpc.service.biz.UserBiz;
import cn.qdcares.rpc.service.dao.impl.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

/**
 * @program: dss-parent
 * @description:
 * @author: dongqun
 * @date: 2018/06/19 13:24
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-hibernate.xml")
public class AuthDaoTest {
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private DeptDao deptDao;
    @Autowired
    private ResourceDao resourcesDao;
    @Autowired
    private RoleResourceDao roleResourceDao;
    @Autowired
    private UserBiz userBiz;

    @Test
    public void userlisttest(){
        //System.out.println(userDao.listAllRoles("zhang1"));
        //System.out.println(userDao.listAllResources("zhang1"));
        //PageParam pageParam = new PageParam(0,10);
        //List<UserDto> userDtos = userDao.listPage(pageParam,new UserDto());
        //List<UserDto> userDtos = userBiz.userList(new UserDto(),0,10);
        //System.out.println(userDtos.size());
        //UserDto userDto = userBiz.getById("zhang7");
        //userBiz.deleteUserById("zhang7");
        //List<RoleDto> roles =  roleDao.findAllRoles(new RoleDto());
        //System.out.println(roles.size());
        //userBiz.deleteUserById("zhang5");
        //List<ResourceDto> lis  =userDao.listAllResources("zhang2");

    }


    /*public void test1(){
        Session session = null;
        try {
            session = sessionFactory.openSession();

        } catch (HibernateException e) {
            e.printStackTrace();
            if(session!=null) session.getTransaction().rollback();
        } finally {
            if(session!=null) session.close();
        }
    }


    public void testUserRole(){
        UserDto userDto = userDao.get(5);
        RoleDto roleDto = roleDao.get(5);
        userDao.addRole(userDto.getUserId(),roleDto);
    }*/
    /*@Test
    public void testAdd(){
        DeptDto deptDto = new DeptDto();
        deptDto.setDeptName("机电部");
        deptDto.setUrlPrefix("/");
        deptDao.add(deptDto);

        DeptDto deptDto2 = new DeptDto();
        deptDto2.setDeptName("运维部");
        deptDto2.setUrlPrefix("/");
        deptDao.add(deptDto2);

        DeptDto deptDto3 = new DeptDto();
        deptDto3.setDeptName("机场服务部");
        deptDto3.setUrlPrefix("/");
        deptDao.add(deptDto3);

        UserDto user1 = new UserDto();
        user1.setUserId("zhang1");
        user1.setUserPwd("123");
        user1.setEmail("dong@126.com");
        user1.setLastLoginTime(new Date());
        user1.setPhone("1234567890");
        user1.setStatus(1);
        user1.setRemark("机场领导");
        userDao.create(user1);

        UserDto user2 = new UserDto();
        user2.setUserId("zhang2");
        user2.setUserPwd("123");
        user2.setEmail("dong@126.com");
        user2.setLastLoginTime(new Date());
        user2.setPhone("1234567890");
        user2.setStatus(1);
        user2.setRemark("机电部领导");
        user2.setDept(deptDto);
        userDao.create(user2);

        UserDto user3 = new UserDto();
        user3.setUserId("zhang3");
        user3.setUserPwd("123");
        user3.setEmail("dong@126.com");
        user3.setLastLoginTime(new Date());
        user3.setPhone("1234567890");
        user3.setStatus(1);
        user3.setRemark("运维部职工");
        user3.setDept(deptDto2);
        userDao.create(user3);

        UserDto user4 = new UserDto();
        user4.setUserId("zhang4");
        user4.setUserPwd("123");
        user4.setEmail("dong@126.com");
        user4.setLastLoginTime(new Date());
        user4.setPhone("1234567890");
        user4.setStatus(1);
        user4.setRemark("普通旅客");
        userDao.create(user4);

        UserDto user5 = new UserDto();
        user5.setUserId("zhang5");
        user5.setUserPwd("123");
        user5.setEmail("dong@126.com");
        user5.setLastLoginTime(new Date());
        user5.setPhone("1234567890");
        user5.setStatus(1);
        user5.setRemark("VIP旅客");
        userDao.create(user5);


        RoleDto roleDto = new RoleDto();
        roleDto.setRoleType(RoleType.leader);
        roleDto.setUser(user1);
        roleDao.add(roleDto);

        RoleDto roleDto2 = new RoleDto();
        roleDto2.setRoleType(RoleType.dept_head);
        roleDto2.setUser(user2);
        roleDao.add(roleDto2);

        RoleDto roleDto3 = new RoleDto();
        roleDto3.setRoleType(RoleType.staff);
        roleDto3.setUser(user3);
        roleDao.add(roleDto3);

        RoleDto roleDto4 = new RoleDto();
        roleDto4.setRoleType(RoleType.vister);
        roleDto4.setUser(user4);
        roleDao.add(roleDto4);

        RoleDto roleDto5 = new RoleDto();
        roleDto5.setRoleType(RoleType.vip_vister);
        roleDto5.setUser(user5);
        roleDao.add(roleDto5);

    }*/

    public void  testResourcesAdd(){
        ResourceDto resourceDto  = new ResourceDto();
        resourceDto.setName("贵宾服务");
        resourceDto.setUrl("/front/*");

        ResourceDto resourceDto2  = new ResourceDto();
        resourceDto2.setName("旅客服务");
        resourceDto2.setUrl("/front/visiter/*");

        ResourceDto resourceDto3  = new ResourceDto();
        resourceDto3.setName("运维服务");
        resourceDto3.setUrl("/back/operation/*");

        ResourceDto resourceDto4  = new ResourceDto();
        resourceDto4.setName("机电服务");
        resourceDto4.setUrl("/back/electric/*");
        resourcesDao.add(resourceDto);
        resourcesDao.add(resourceDto2);
        resourcesDao.add(resourceDto3);
        resourcesDao.add(resourceDto4);
    }

    public void testRoleResourcesAdd(){
        /*UserDto user1 = userDao.get(1L);

        RoleDto roleDto = new RoleDto();
        roleDto.setRoleName("机场总协调2");
        roleDto.setRoleType(RoleType.leader);
        roleDto.setUser(user1);
        roleDao.add(roleDto);*/


        /*ResourceDto r1 = new ResourceDto();
        r1.setUrl("/sss");
        r1.setName("测试1");
        ResourceDto r2 = new ResourceDto();
        r2.setUrl("/sswws");
        r2.setName("测试2");
        ResourceDto r3 = new ResourceDto();
        r3.setUrl("/swwqss");
        r3.setName("测试3");
        resourcesDao.add(r1);
        resourcesDao.add(r2);
        resourcesDao.add(r3);*/

        /*RoleResourceDto resourceDto = new RoleResourceDto();
        resourceDto.setRoleid(1L);
        resourceDto.setResid(5L);
        roleResourceDao.add(resourceDto);

        resourceDto = new RoleResourceDto();
        resourceDto.setRoleid(1L);
        resourceDto.setResid(6L);
        roleResourceDao.add(resourceDto);

        resourceDto = new RoleResourceDto();
        resourceDto.setRoleid(1L);
        resourceDto.setResid(7L);
        roleResourceDao.add(resourceDto);*/

        //UserDto userDto = userDao.get(2L);

        /*List<RoleDto> roles = userDao.listAllRoles(userDto.getUserId());
        for (RoleDto role:roles){
            System.out.println(role.getRoleName()+":"+role.getRoleType().getType());
        }*/
        /*List<ResourceDto> resources = userDao.listAllResources(userDto.getUserId());
        for (ResourceDto resource:resources){
            System.out.println(resource.getUrl());
        }*/

        /*DeptDto dept = userDao.getDept(userDto.getUserId());
        System.out.println(dept.getDeptName());*/

        /*DeptDto deptDto = new DeptDto();
        deptDto.setDeptName("阿拉善22");
        deptDto.setDeptDescribe("及时完成");
        deptDto.setUrlPrefix("/sssaaaas");
        deptDao.add(deptDto);
        userDto.setDept(deptDto);
        userDao.update(userDto);*/



    }

    public void test2(){
        UserDto userDto = userDao.get(2L);
        DeptDto deptDto = new DeptDto();
        deptDto.setDeptName("阿拉善end");
        deptDto.setDeptDescribe("及时完成");
        deptDto.setUrlPrefix("/sssaaaas");
        //deptDao.add(deptDto);
        userDao.addDept(userDto.getUserId(),deptDto);
    }

    public void test(){
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.getTransaction().begin();
            //UserDto userDto = userDao.get(2L);
            UserDto userDto = (UserDto) session.get(UserDto.class,2);
            DeptDto deptDto = new DeptDto();
            deptDto.setDeptName("阿拉善22");
            deptDto.setDeptDescribe("及时完成");
            deptDto.setUrlPrefix("/sssaaaas");
            session.save(deptDto);
            //deptDao.add(deptDto);
            userDto.setDept(deptDto);
            //userDao.update(userDto);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if(session!=null) session.getTransaction().rollback();
        }finally {
            if (session!=null) session.close();
        }
    }


}
