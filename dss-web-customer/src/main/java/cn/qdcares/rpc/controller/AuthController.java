package cn.qdcares.rpc.controller;

import cn.qdcares.rpc.common.page.PageBean;
import cn.qdcares.rpc.common.page.PageParam;
import cn.qdcares.rpc.common.utils.StringUtil;
import cn.qdcares.rpc.common.web.FunctionUrlMap;
import cn.qdcares.rpc.common.web.constant.ResultConstant;
import cn.qdcares.rpc.facade.model.ResourceDto;
import cn.qdcares.rpc.facade.model.RoleDto;
import cn.qdcares.rpc.facade.model.RoleResourceDto;
import cn.qdcares.rpc.facade.service.AuthServiceFacade;
import cn.qdcares.rpc.web.AuthUtil;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @program: dss-parent
 * @description: 角色管理
 * @author: dongqun
 * @date: 2018/06/25 09:17
 **/
@Controller
@RequestMapping("/back/authInfo")
public class AuthController {

    @Autowired
    private AuthServiceFacade authService;

    @RequestMapping(value = "/rolePage",method ={RequestMethod.GET,RequestMethod.POST} )
    public ModelAndView rolePage(){
        return new ModelAndView("/back/system/roleinfo");
    }


    @RequestMapping(value = "/resourcePage",method ={RequestMethod.GET,RequestMethod.POST} )
    public ModelAndView resourcePage(){
        return new ModelAndView("/back/system/resourceinfo");
    }

    @RequestMapping(value = "/authPage",method ={RequestMethod.GET,RequestMethod.POST} )
    public ModelAndView authPage(HttpServletRequest request){
        String rid = request.getParameter("roleid");
        return new ModelAndView("/back/system/authinfo");
    }

    @RequestMapping(value = "/addRole",method =RequestMethod.POST)
    @ResponseBody
    public ModelMap addRole(RoleDto role){
        ModelMap map = new ModelMap();
        RoleDto roleDto = null;
        try {
            roleDto = authService.addRole(role);
        } catch (Exception e) {
            map.addAttribute(ResultConstant.RESPONSE_STATUS,ResultConstant.RESPONSE_STATUS_FAIL);
            e.printStackTrace();
            return map;
        }
        if (roleDto!=null){
            map.addAttribute(ResultConstant.RESPONSE_RETURN_ENTITY,roleDto);
        }
        map.addAttribute(ResultConstant.RESPONSE_STATUS,ResultConstant.RESPONSE_STATUS_SUCCESS);
        return map;
    }

    @RequestMapping(value = "/deleteRole",method =RequestMethod.POST)
    @ResponseBody
    public ModelMap deleteRole(RoleDto role){
        ModelMap map = new ModelMap();
        try {
             authService.deleteRole(role);
        } catch (Exception e) {
            e.printStackTrace();
            map.addAttribute(ResultConstant.RESPONSE_STATUS,ResultConstant.RESPONSE_STATUS_FAIL);
            return map;
        }
        map.addAttribute(ResultConstant.RESPONSE_STATUS,ResultConstant.RESPONSE_STATUS_SUCCESS);
        return map;
    }


    @RequestMapping(value = "/updateRole",method =RequestMethod.POST)
    @ResponseBody
    public ModelMap updateRole(RoleDto role){
        ModelMap map = new ModelMap();
        RoleDto roleDto = null;
        try {
            roleDto = authService.update(role);
        } catch (Exception e) {
            map.addAttribute(ResultConstant.RESPONSE_STATUS,ResultConstant.RESPONSE_STATUS_FAIL);
            e.printStackTrace();
            return map;
        }
        if (roleDto!=null){
            map.addAttribute(ResultConstant.RESPONSE_RETURN_ENTITY,role);
        }
        map.addAttribute(ResultConstant.RESPONSE_STATUS,ResultConstant.RESPONSE_STATUS_SUCCESS);
        return map;
    }


    @RequestMapping(value = "/findRole",method ={RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public ModelMap findRole(RoleDto role){
        ModelMap map = new ModelMap();
        RoleDto roleDto = null;
        try {
            roleDto = authService.findRole(role);
        } catch (Exception e) {
            map.addAttribute(ResultConstant.RESPONSE_STATUS,ResultConstant.RESPONSE_STATUS_FAIL);
            e.printStackTrace();
            return map;
        }
        if (roleDto!=null){
            map.addAttribute(ResultConstant.RESPONSE_RETURN_ENTITY,role);
        }
        map.addAttribute(ResultConstant.RESPONSE_STATUS,ResultConstant.RESPONSE_STATUS_SUCCESS);
        return map;
    }


    @RequestMapping(value = "/rolePageList",method ={RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public ModelMap rolePageList(HttpServletRequest request){
        ModelMap map = new ModelMap();
        int currpage = Integer.parseInt(request.getParameter("currpage"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        String id = request.getParameter("id");
        String roleName = request.getParameter("roleName");
        RoleDto role = new RoleDto();
        if (!StringUtil.isEmpty(id)) role.setId(Long.valueOf(id));
        if (!StringUtil.isEmpty(roleName)) role.setRoleName(roleName);
        PageParam param = new PageParam(currpage,pageSize);
        PageBean<RoleDto> roleDtoList = null;
        try {
            roleDtoList = authService.findRoleByPage(param,role);
        } catch (Exception e) {
            map.addAttribute(ResultConstant.RESPONSE_STATUS,ResultConstant.RESPONSE_STATUS_FAIL);
            e.printStackTrace();
            return map;
        }
        if (roleDtoList!=null){
            map.addAttribute(ResultConstant.RESPONSE_ROWS,roleDtoList.getRows());
            map.addAttribute(ResultConstant.RESPONSE_TOTAL,roleDtoList.getTotal());
        }
        map.addAttribute(ResultConstant.RESPONSE_STATUS,ResultConstant.RESPONSE_STATUS_SUCCESS);
        return map;
    }


    @RequestMapping(value = "/rolelist",method ={RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public ModelMap roleList(HttpServletRequest request){
        ModelMap map = new ModelMap();
        String id = request.getParameter("id");
        String roleName = request.getParameter("roleName");
        RoleDto role = new RoleDto();
        if (!StringUtil.isEmpty(id)) role.setId(Long.valueOf(id));
        if (!StringUtil.isEmpty(roleName)) role.setRoleName(roleName);
        List<RoleDto> roleDtoList = null;
        try {
            roleDtoList = authService.findAllRoles(role);
        } catch (Exception e) {
            map.addAttribute(ResultConstant.RESPONSE_STATUS,ResultConstant.RESPONSE_STATUS_FAIL);
            e.printStackTrace();
            return map;
        }
        if (roleDtoList!=null && roleDtoList.size()>0){
            map.addAttribute(ResultConstant.RESPONSE_RETURN_LIST,roleDtoList);
        }
        map.addAttribute(ResultConstant.RESPONSE_STATUS,ResultConstant.RESPONSE_STATUS_SUCCESS);
        return map;
    }

    @RequestMapping(value = "/addResource",method =RequestMethod.POST)
    @ResponseBody
    public ModelMap addResource(ResourceDto resource){
        ModelMap map = new ModelMap();
        ResourceDto resourceDto = null;
        try {
            resourceDto = authService.addResource(resource);
        } catch (Exception e) {
            map.addAttribute(ResultConstant.RESPONSE_STATUS,ResultConstant.RESPONSE_STATUS_FAIL);
            e.printStackTrace();
            return map;
        }
        if (resourceDto!=null){
            map.addAttribute(ResultConstant.RESPONSE_RETURN_ENTITY,resourceDto);
        }
        map.addAttribute(ResultConstant.RESPONSE_STATUS,ResultConstant.RESPONSE_STATUS_SUCCESS);
        return map;
    }

    @RequestMapping(value = "/deleteResource",method =RequestMethod.POST)
    @ResponseBody
    public ModelMap deleteResource(ResourceDto resource){
        ModelMap map = new ModelMap();
        try {
            authService.deleteResource(resource);
        } catch (Exception e) {
            e.printStackTrace();
            map.addAttribute(ResultConstant.RESPONSE_STATUS,ResultConstant.RESPONSE_STATUS_FAIL);
            return map;
        }
        map.addAttribute(ResultConstant.RESPONSE_STATUS,ResultConstant.RESPONSE_STATUS_SUCCESS);
        return map;
    }


    @RequestMapping(value = "/updateResource",method =RequestMethod.POST)
    @ResponseBody
    public ModelMap updateResource(ResourceDto resource){
        ModelMap map = new ModelMap();
        ResourceDto resourceDto = null;
        try {
            resourceDto = authService.updateResource(resource);
        } catch (Exception e) {
            map.addAttribute(ResultConstant.RESPONSE_STATUS,ResultConstant.RESPONSE_STATUS_FAIL);
            e.printStackTrace();
            return map;
        }
        if (resourceDto!=null){
            map.addAttribute(ResultConstant.RESPONSE_RETURN_ENTITY,resourceDto);
        }
        map.addAttribute(ResultConstant.RESPONSE_STATUS,ResultConstant.RESPONSE_STATUS_SUCCESS);
        return map;
    }


    @RequestMapping(value = "/findResource",method ={RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public ModelMap findResource(ResourceDto resource){
        ModelMap map = new ModelMap();
        ResourceDto resourceDto = null;
        try {
            resourceDto = authService.findResource(resource);
        } catch (Exception e) {
            map.addAttribute(ResultConstant.RESPONSE_STATUS,ResultConstant.RESPONSE_STATUS_FAIL);
            e.printStackTrace();
            return map;
        }
        if (resourceDto!=null){
            map.addAttribute(ResultConstant.RESPONSE_RETURN_ENTITY,resourceDto);
        }
        map.addAttribute(ResultConstant.RESPONSE_STATUS,ResultConstant.RESPONSE_STATUS_SUCCESS);
        return map;
    }

    @RequestMapping(value = "/resourcePageList",method ={RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public ModelMap resourcePageList(HttpServletRequest request){
        ModelMap map = new ModelMap();
        int currpage = Integer.parseInt(request.getParameter("currpage"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        PageParam param = new PageParam(currpage,pageSize);
        PageBean<ResourceDto> pageBean = null;
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        ResourceDto resource = new ResourceDto();
        if (!StringUtil.isEmpty(id)) resource.setId(Long.valueOf(id));
        if(!StringUtil.isEmpty(name)) resource.setName(name);
        try {
            pageBean = authService.findResourceByPage(param,resource);
        } catch (Exception e) {
            map.addAttribute(ResultConstant.RESPONSE_STATUS,ResultConstant.RESPONSE_STATUS_FAIL);
            e.printStackTrace();
            return map;
        }
        if (pageBean!=null){
            map.addAttribute(ResultConstant.RESPONSE_ROWS,pageBean.getRows());
            map.addAttribute(ResultConstant.RESPONSE_TOTAL,pageBean.getTotal());
        }
        map.addAttribute(ResultConstant.RESPONSE_STATUS,ResultConstant.RESPONSE_STATUS_SUCCESS);
        return map;
    }

    @RequestMapping(value = "/authResource",method ={RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public ModelMap authResource(HttpServletRequest request){
        ModelMap map = new ModelMap();
        String rid = request.getParameter("rid");
        String resid = request.getParameter("resid");
        if (!StringUtil.isEmpty(rid) && !StringUtil.isEmpty(rid)){
            RoleDto role = new RoleDto();
            role.setId(Long.valueOf(rid));
            ResourceDto resource = new ResourceDto();
            resource.setId(Long.valueOf(resid));
            RoleResourceDto roleResourceDto = null;
            try {
                roleResourceDto = authService.authResource(role,resource);
            } catch (Exception e) {
                e.printStackTrace();
                map.addAttribute(ResultConstant.RESPONSE_STATUS,ResultConstant.RESPONSE_STATUS_FAIL);
                return map;
            }
            if (roleResourceDto!=null){
                map.addAttribute(ResultConstant.RESPONSE_RETURN_ENTITY,roleResourceDto);
            }
            map.addAttribute(ResultConstant.RESPONSE_STATUS,ResultConstant.RESPONSE_STATUS_SUCCESS);
        }else {
            map.addAttribute(ResultConstant.RESPONSE_STATUS,ResultConstant.RESPONSE_STATUS_FAIL);
        }

        return map;
    }

    @RequestMapping(value = "/unauthResource",method ={RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public ModelMap unauthResource(HttpServletRequest request){
        ModelMap map = new ModelMap();
        String rid = request.getParameter("rid");
        String resid = request.getParameter("resid");
        if (!StringUtil.isEmpty(rid) && !StringUtil.isEmpty(rid)){
            RoleDto role = new RoleDto();
            role.setId(Long.valueOf(rid));
            ResourceDto resource = new ResourceDto();
            resource.setId(Long.valueOf(resid));
            try {
                authService.unAuthResource(role,resource);
            } catch (Exception e) {
                e.printStackTrace();
                map.addAttribute(ResultConstant.RESPONSE_STATUS,ResultConstant.RESPONSE_STATUS_FAIL);
                return map;
            }
            map.addAttribute(ResultConstant.RESPONSE_STATUS,ResultConstant.RESPONSE_STATUS_SUCCESS);
        }else {
            map.addAttribute(ResultConstant.RESPONSE_STATUS,ResultConstant.RESPONSE_STATUS_FAIL);
        }
        return map;
    }


    @RequestMapping(value = "/authResourceBat",method ={RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public ModelMap authResourceBat(HttpServletRequest request){
        ModelMap map = new ModelMap();
        String rid = request.getParameter("rid");
        String rname = request.getParameter("rname");
        String reslist = request.getParameter("list");
        if (!StringUtil.isEmpty(reslist)){
            List<FunctionUrlMap> funs = JSON.parseArray(reslist,FunctionUrlMap.class);
            RoleDto role = new RoleDto();
            if (!StringUtil.isEmpty(rid))  role.setId(Long.valueOf(rid));
            if (!StringUtil.isEmpty(rname)) role.setRoleName(rname);
            List<RoleResourceDto> roleResources = null;
            List<ResourceDto> resources = new ArrayList<>();
            for (FunctionUrlMap m: funs){
                ResourceDto resourceDto = new ResourceDto();
                resourceDto.setId(m.getId());
                resources.add(resourceDto);
            }
            try {
                roleResources = authService.authResourceBat(role,resources);
            } catch (Exception e) {
                e.printStackTrace();
                map.addAttribute(ResultConstant.RESPONSE_STATUS,ResultConstant.RESPONSE_STATUS_FAIL);
                return map;
            }
            if (roleResources!=null){
                map.addAttribute(ResultConstant.RESPONSE_RETURN_LIST,roleResources);
            }
            map.addAttribute(ResultConstant.RESPONSE_STATUS,ResultConstant.RESPONSE_STATUS_SUCCESS);
        }else {
            map.addAttribute(ResultConstant.RESPONSE_STATUS,ResultConstant.RESPONSE_STATUS_FAIL);
        }
        return map;
    }

    @RequestMapping(value = "/unauthResourceBat",method ={RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public ModelMap unauthResourceBat(HttpServletRequest request){
        ModelMap map = new ModelMap();
        String rid = request.getParameter("rid");
        String reslist = request.getParameter("list");
        if (!StringUtil.isEmpty(reslist)){
            List<FunctionUrlMap> funs = JSON.parseArray(reslist,FunctionUrlMap.class);
            RoleDto role = new RoleDto();
            if (!StringUtil.isEmpty(rid))  role.setId(Long.valueOf(rid));
            List<ResourceDto> resources = new ArrayList<>();
            for (FunctionUrlMap m: funs){
                ResourceDto resourceDto = new ResourceDto();
                resourceDto.setId(m.getId());
                resources.add(resourceDto);
            }
            try {
                authService.unAuthResourceBat(role,resources);
            } catch (Exception e) {
                e.printStackTrace();
                map.addAttribute(ResultConstant.RESPONSE_STATUS,ResultConstant.RESPONSE_STATUS_FAIL);
                return map;
            }
            map.addAttribute(ResultConstant.RESPONSE_STATUS,ResultConstant.RESPONSE_STATUS_SUCCESS);
        }else {
            map.addAttribute(ResultConstant.RESPONSE_STATUS,ResultConstant.RESPONSE_STATUS_FAIL);
        }
        return map;
    }

    @RequestMapping("/resourcetree")
    public ModelAndView resourceTree(HttpServletRequest request){
        String roleid = request.getParameter("roleid");
        ModelAndView modelAndView = new ModelAndView("/back/system/resourceset");
        List<FunctionUrlMap> fulist = AuthUtil.initAuth(AuthUtil.PACKAGENAME);
        String str_tree = JSON.toJSONString(fulist);
        List<RoleResourceDto> rrlist = authService.hasResources(Long.valueOf(roleid));
        if (rrlist!=null && rrlist.size()>0){
            modelAndView.addObject("having",JSON.toJSONString(rrlist));
        }else {
            modelAndView.addObject("having","[]");
        }
        modelAndView.addObject("roleid",roleid);
        modelAndView.addObject("tree",str_tree);
        return modelAndView;
    }
}
