package com.springboot05security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

@EnableWebSecurity // 开启WebSecurity模式
@Configuration//标注这个是一个配置类
public class MyWebSecurityConfig extends WebSecurityConfigurerAdapter {



    //取消密码验证  只不过这个被废弃了这个方法  不建议使用
    @Bean
    PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    //授权

    //这个有三个configure，是重载的  你要看清楚你想用哪个
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //定制自己的需求
        //我们说明首页所有人都可以访问 ，但是level1/level2等等的文件夹下面的具体界面需要不同等级的人才能访问
        //下面这句话的意思是先注册一个认证请求，然后其中所有的人也就是所有的请求"/"链接的是首页的地址，可以被所有人访问
        //这个是一个链式编程 要注意
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/index.html").permitAll()
                //这个表示所有/level1/1/下面所有的权限只有是角色是vip1的人才能访问，这里真的是路径控制，不是你的资源位置，要注意
                .antMatchers("/level/1/**").hasRole("vip1")
                .antMatchers("/level/2/**").hasRole("vip2")
                .antMatchers("/level/3/**").hasRole("vip3")
                .anyRequest().authenticated();//其他的需要授权后访问  就比如即使我现在开启了/aaa请求映射到首页，因为他不在放行的白名单里面他也进不去首页


        //上面是控制了哪些权限可以访问 我们在笔记中可以看到我们的截图
        //如果没有权限会报403错误，我们也可以让他跳转到springboot默认登陆界面，只要开启就行了
        //没有权限默认跳到登陆界面
        // 开启自动配置的登录功能
        // /login 请求来到登录页
        // /login?error 重定向到这里表示登录失败
        http.formLogin();//但是你没有用户名和密码所以就登陆不了所以我们下面添加用户名和密码和角色


        //上面已经有了登录请求 下面写注销请求点进去源码我们可以发现我们注销默认发送/logout请求进行在注销功能
        //开启了注销功能，以及定制注销后去往哪个界面
        http.csrf().disable();
        http.logout().logoutSuccessUrl("/aaa");
        //开启记住我功能
        http.rememberMe();
    }




    //认证

    //密码编码PasswordEncoder
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //认证的方式有很多 一个是内存验证一个是数据库验证，看需求你用哪个 先介绍内存验证

        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())//这里加了密码验证  下面设置密码的时候也要输入相应编码的验证规则
                .withUser("miaoyongjin").password(new BCryptPasswordEncoder().encode("123456")).roles("vip2","vip3")
                .and()
                .withUser("admin").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1","vip2","vip3")
                .and()
                .withUser("guest").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1","vip2");
    }




    //认证
//
//    //密码编码PasswordEncoder
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        //认证的方式有很多 一个是内存验证一个是数据库验证，看需求你用哪个 先介绍内存验证
//
//        auth.inMemoryAuthentication()//这里取消了验证规则 所以下面的密码也不能使用密码编码
//                .withUser("miaoyongjin").password("123456").roles("vip2","vip3")
//                .and()
//                .withUser("admin").password("123456").roles("vip1","vip2","vip3")
//                .and()
//                .withUser("guest").password("123456").roles("vip1","vip2");
//    }
}
