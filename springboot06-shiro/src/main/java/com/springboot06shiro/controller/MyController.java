package com.springboot06shiro.controller;

import com.springboot06shiro.service.UserSevice;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {




    @RequestMapping({"/","/index"})
    public  String index() {
        return "index";
    }
    @RequestMapping("/user/add")
    public  String add() {
        return "user/add";
    }
    @RequestMapping({"/user/update"})
    public  String update() {
        return "/user/update";
    }
    @RequestMapping({"/toLogin"})
    public  String toLogin() {
        return "login";
    }


    //首先获得前台用户输入的用户名和密码
    @RequestMapping({"/login"})
    public String toLogin(String username, String password, Model model) {
        //获得当前用户
        Subject subject = SecurityUtils.getSubject();
        //通过用户名和密码获得令牌
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        //执行登录
        try {
            //尝试登陆 这个时候会走我们自定义的UserRealm的认证阶段   失败会有提示 成功就访问首页
            subject.login(token);
            return "index";
        }catch (UnknownAccountException e){
            model.addAttribute("msg","用户名错误");
            return "login";
        }catch (IncorrectCredentialsException e){
            model.addAttribute("msg","密码错误");
            return "login";

        }catch (AuthenticationException e){
            model.addAttribute("msg","登录有误");
            return "login";

        }

    }


    @RequestMapping({"/unauthorized"})
    @ResponseBody
    public  String unauthorized() {
        return "未经授权不能访问";
    }


}