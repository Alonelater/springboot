package com.springboot03.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHanderInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取session  只要登录了就有
        String user = (String) request.getSession().getAttribute("user");
        if (user!=null){
            return true;
        }else {
            request.setAttribute("msg","请先登录,否则没有操作权限");
            request.getRequestDispatcher("/index.html").forward(request,response);
            return false;
        }

    }
}
