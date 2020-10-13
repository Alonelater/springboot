package com.springboot03.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration//自己写的mvc配置类
public class MyMvcConfig implements WebMvcConfigurer {


    //由于首页没有任何逻辑 直接写一个controller不合适 所以我们使用扩展mvc来实现页面跳转

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index.html").setViewName("index");
        //添加登录界面的视图映射
        registry.addViewController("/main.html").setViewName("dashboard");
    }


    //添加国际化组件  我们已经写好了javaBean类
    @Bean
    public LocaleResolver localeResolver(){
        return  new MyLocaleResolver();
    }


//    //添加拦截器组件
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        //拦截全部请求
//        registry.addInterceptor(new LoginHanderInterceptor()).addPathPatterns("/**").
//                //在拦截这些所有请求下面不拦截以下规则的内容，有静态的，也有非静态的
//                        excludePathPatterns("/","/index.html","/user/login","/asserts/**");
//    }
}
