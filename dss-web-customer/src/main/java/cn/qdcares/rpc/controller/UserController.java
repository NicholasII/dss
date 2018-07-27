package cn.qdcares.rpc.controller;

import cn.qdcares.rpc.common.page.PageBean;
import cn.qdcares.rpc.common.utils.StringUtil;
import cn.qdcares.rpc.common.web.BaseController;
import cn.qdcares.rpc.common.web.constant.ResultConstant;
import cn.qdcares.rpc.facade.model.RoleDto;
import cn.qdcares.rpc.facade.model.UserDto;
import cn.qdcares.rpc.facade.service.AuthServiceFacade;
import cn.qdcares.rpc.facade.service.UserServiceFacade;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @program: dss-parent
 * @description: 用户controller
 * @author: dongqun
 * @date: 2018/06/02 11:12
 * 注解：@ApiModel 和 @ApiModelProperty 用于在通过对象接收参数时在API文档中显示字段的说明
 * 注解：@DateTimeFormat 和 @JsonFormat 用于在接收和返回日期格式时将其格式化
 **/
@Controller
@RequestMapping(value = "/back/userInfo",produces = "application/json")
@Api(value = "用户信息", description = "用户信息相关接口",tags = "UserController",produces = "application/json")
public class UserController extends BaseController{

    @Autowired
    private UserServiceFacade userService;

    @Autowired
    private AuthServiceFacade authService;

    @RequestMapping(value = "/page",method ={RequestMethod.GET,RequestMethod.POST} )
    @ApiOperation(value = "用户管理页面")
    public ModelAndView page(){
        return new ModelAndView("/back/system/userinfo");
    }

    @RequestMapping(value = "/rolePage",method ={RequestMethod.GET,RequestMethod.POST} )
    @ApiOperation(value = "用户角色配置页面")
    public ModelAndView rolePage(){
        return new ModelAndView("/back/system/userRoleInfo");
    }

    @RequestMapping(value = "/deptPage",method ={RequestMethod.GET,RequestMethod.POST} )
    @ApiOperation(value = "用户部门配置页面")
    public ModelAndView deptPage(){
        return new ModelAndView("/back/system/userDeptInfo");
    }

    @ResponseBody
    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    @ApiOperation(value = "添加用户",notes="添加用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户ID",required = true, paramType = "query"),
            @ApiImplicitParam(name = "userName",value = "用户名称",required = true, paramType = "query"),
            @ApiImplicitParam(name = "userPwd",value = "密码",required = true, paramType = "query"),
            @ApiImplicitParam(name = "phone",value = "手机号",required = true, paramType = "query"),
            @ApiImplicitParam(name = "email",value = "邮箱",required = true, paramType = "query"),
            @ApiImplicitParam(name = "status",value = "状态(1:可用，0:不可用)",required = true, paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "remark",value = "描述", paramType = "query")
    })
    public ModelMap addUser(UserDto userDto,HttpServletRequest request){
        String role = request.getParameter("roleId");
        List<Long> rids = getRoles(role);
        try {
            userService.create(userDto,rids);

        } catch (Exception e) {
            return new ModelMap().addAttribute("status","fail");
        }
        return new ModelMap().addAttribute("status","success");
    }

    private List<Long> getRoles(String role){
        List<Long> rids = new ArrayList<>();
        if (!StringUtil.isEmpty(role)){
            String[] roles = role.split(",");
            if (roles.length>1){
                for (int i=0;i<roles.length;i++){
                    rids.add(Long.valueOf(roles[i]));
                }
            }else if (roles.length==1){
                rids.add(Long.valueOf(roles[0]));
            }
        }
        return rids;
    }

    @RequestMapping(value="/getUserById",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "查询用户",notes = "根据ID查询用户")
    public ModelMap getUserById(String userId){
        UserDto user = null;
        try {
            user = userService.getById(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelMap().addAttribute("status","fail");
        }
        return new ModelMap().addAttribute("user", user).addAttribute("status","success");
    }

    @RequestMapping(value="/getUserByName",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    @ApiOperation(value = "查询用户",notes = "根据名称查询用户")
    public ModelMap getUserByName(String name){
        UserDto user = null;
        try {
            user = userService.findUserByUserName(name);
        } catch (Exception e) {
            return new ModelMap().addAttribute("status","fail");
        }
        return new ModelMap().addAttribute("user",user);
    }

    @RequestMapping(value="/updateUser",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "更新用户",notes = "更新用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName",value = "用户名称",required = true, paramType = "query"),
            @ApiImplicitParam(name = "userPwd",value = "密码",required = false, paramType = "query"),
            @ApiImplicitParam(name = "phone",value = "手机号", paramType = "query"),
            @ApiImplicitParam(name = "email",value = "邮箱",paramType = "query"),
            @ApiImplicitParam(name = "status",value = "状态(1:可用，0:不可用)",paramType = "query",dataType = "Integer"),
            @ApiImplicitParam(name = "remark",value = "描述", paramType = "query")
    })
    public ModelMap updateUser(UserDto user,HttpServletRequest request){
        String role = request.getParameter("roleId");
        List<Long> rids = getRoles(role);
        try {
            userService.update(user,rids);
        } catch (Exception e) {
            return new ModelMap().addAttribute("status","fail");
        }
        return new ModelMap().addAttribute("user",user).addAttribute("status","success");
    }

    @RequestMapping(value="/updatePwd",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "修改用户密码",notes = "修改用户密码")
    public ModelMap updatePwd(String userId,String newPwd,boolean isChange){
        try {
            userService.updateUserPwd(userId,newPwd,isChange);
        } catch (Exception e) {
            return new ModelMap().addAttribute("status","fail");
        }
        return new ModelMap().addAttribute("status","success");
    }


    @RequestMapping(value="/deleteUser",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "删除用户",notes = "删除用户")
    public ModelMap deleteUser(String userId){
        try {
            userService.deleteUserById(userId);
        } catch (Exception e) {
            return new ModelMap().addAttribute("status","fail");
        }
        return new ModelMap().addAttribute("status","success");
    }

    @RequestMapping(value="/userPageList",method = RequestMethod.POST)
    @ResponseBody
    public ModelMap userPageList(HttpServletRequest request, HttpServletResponse response){
        ModelMap map = new ModelMap();
        int currpage = Integer.parseInt(request.getParameter("currpage"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        String userId = request.getParameter("userId;");
        String userName = request.getParameter("userName");
        UserDto userDto = new UserDto();
        if (!StringUtil.isEmpty(userId)){
            userDto.setUserId(userId);
        }
        if (!StringUtil.isEmpty(userName)){
            userDto.setUserName(userName);
        }
        PageBean<UserDto> userDtos = userService.userPageList(userDto,currpage,pageSize);
        map.addAttribute(ResultConstant.RESPONSE_ROWS,userDtos.getRows());
        map.addAttribute(ResultConstant.RESPONSE_TOTAL,userDtos.getTotal());
        return map;
    }

    @RequestMapping(value="/roleList",method = RequestMethod.POST)
    @ResponseBody
    public ModelMap roleList(String userid){
        ModelMap map = new ModelMap();
        List<RoleDto> havingRoles = userService.listAllRoles(userid);
        List<RoleDto> allRoles = authService.findAllRoles(new RoleDto());
        map.addAttribute("all",allRoles);
        map.addAttribute("having",havingRoles);
        return map;
    }

}
