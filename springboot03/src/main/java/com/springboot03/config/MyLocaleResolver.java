package com.springboot03.config;

import org.springframework.context.annotation.Bean;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * 实现国际化 那我们就要实现 没有写@Configuration  所以我门要将组件放在有@Configuration的类下面
 */
public class MyLocaleResolver implements LocaleResolver {
    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest) {
        //得到我们设置的参数
        String lanuages = httpServletRequest.getParameter("l");

        Locale locale = null;
        //如果不为空 那就将我们得到的地区信息设置进去
        if (!StringUtils.isEmpty(lanuages)) {

            String[] s = lanuages.split("_");
            locale = new Locale(s[0], s[1]);

        } else {
            //如果为空就使用默认的
            locale = Locale.getDefault();
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }
    //添加国际化组件  我们已经写好了javaBean类
    @Bean
    public LocaleResolver localeResolver(){
        return  new MyLocaleResolver();
    }
}
