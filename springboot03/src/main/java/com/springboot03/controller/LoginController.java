package com.springboot03.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * 登录控制器
 */
@Controller
public class LoginController {

    //因为数据库是伪造的，所以只要密码用户名不为空就让他登录成功  我们看到我们的index界面有问题 没有一些name属性 我们改一改，增加登陆失败提示


    @RequestMapping("/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model, HttpSession session) {
        if (!StringUtils.isEmpty(username) && "123456".equals(password)) {
            //登录成功设置session
            session.setAttribute("user",username);
            return "redirect:/main.html";//在视图跳转添加一个路径然后让他经过视图控制器转发到真正的视图，直接让他访问后台 省的我们的get请求被看了用户名和密码
        } else {
            model.addAttribute("msg", "用户名或密码错误");
            return "/index";//不能直接返回/ 因为这个是要拼接.html的那样也找不到这个/index的界面，如果用了model就不能用重定向，因为这个重新发过请求 是二次请求 model数据携带不过去

        }

    }
}
