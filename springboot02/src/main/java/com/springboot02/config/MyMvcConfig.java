package com.springboot02.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * 这是自己写的mvc配置类，如果不是全面接管就一定不要加@EnableWebMvc
 */
@Configuration//标注这个是一个配置类
@EnableWebMvc// 全面接管SpringMVC 所有都是我们自己配置；所有的springboot帮我们的配置的SpringMVC的自动配置都失效了
public class MyMvcConfig implements WebMvcConfigurer {//实现WebMvcConfigurer 接口 想要什么功能就定制什么功能


    //前面已经看到了这个视图解析器是怎么定制的  现在我们看看怎么添加视图跳转
    //因为因为向首页这个我们是放在了templates里面，并且我们引入了thymeleaf模板引擎，这个模板引擎会帮我们去找和这个文件夹里面的index自动作为首页跳转，不需要写逻辑控制器
    //  所以springboot在我们访问localhost://8080自动显示
    //但是我们现在有一个success.html 里面我们也没有任何数据显示 只是一张页面我们也不想在controller里面增加一个这个进行跳转 ，那应该怎么办呢
    //所以就有了下面这个

    //添加视图控制
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/success").setViewName("success");
    }
}
