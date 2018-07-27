package cn.qdcares.rpc.controller;

import cn.qdcares.rpc.common.web.constant.ResultConstant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;



/**
 * @program: dss-parent
 * @description: 登录
 * @author: dongqun
 * @date: 2018/06/19 17:12
 **/
@Controller
@Api(value = "登录接口", description = "用户登录相关接口",tags = "LoginController",produces = "application/json")
public class LoginController {

    @RequestMapping("/login")
    @ApiOperation(value = "后台登陆接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username",value = "用户名称", required = true, paramType = "query"),
            @ApiImplicitParam(name = "password",value = "用户密码", required = true, paramType = "query")
    })
    public ModelAndView login(String username, String password){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        String emsg =null;
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            ModelAndView modelAndView =  new ModelAndView("unauth");
            modelAndView.addObject("emsg",e.getMessage());
            return modelAndView;
        }
        return new ModelAndView("index");
    }

    @RequestMapping("/mobilelogin")
    @ApiOperation(value = "移动端登陆接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username",value = "用户名称", required = true, paramType = "query"),
            @ApiImplicitParam(name = "password",value = "用户密码", required = true, paramType = "query")
    })
    public ModelMap mobileLogin(String username, String password){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        ModelMap map =  new ModelMap();
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            map.addAttribute(ResultConstant.RESPONSE_STATUS,"unauth");
            return map;
        }
        map.addAttribute(ResultConstant.RESPONSE_STATUS,ResultConstant.RESPONSE_STATUS_SUCCESS);
        return map;
    }

    @RequestMapping("/logout")
    public ModelAndView loginout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return new ModelAndView("redirect:/");
    }
}
