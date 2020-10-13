package com.springboot02.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import java.util.Locale;

/**
 *
 * 这是自己写的mvc配置类，如果不是全面接管就一定不要加
 *   //现在我们需要增加一个视图解析器
 */
@Configuration
public class MyViewResolver implements ViewResolver {

    //我们写一个类，视图解析器就需要实现ViewResolver接口
    @Bean
    public ViewResolver MyViewResolver(){
        return new MyViewResolver();
    }




    @Override
    public View resolveViewName(String s, Locale locale) throws Exception {
        return null;
    }
}
