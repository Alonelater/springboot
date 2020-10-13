package com.springboot06shiro.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.LinkedHashMap;
import java.util.Map;
@Configuration//标注这个是一个配置类 一定要加上  不然下面的@Bean 没用
public class ShiroConfig {

    //创建realm对象  需要自定义类
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }


    //创建DefaultWebSecurityManager创建shiro安全管理器 管理所有的realm对象    需要用到上面创建的realm对象
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联realm对象
        securityManager.setRealm(userRealm());


        return securityManager;
    }

    //设置shiro的拦截  管理所有的secutrityManager
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(defaultWebSecurityManager());

        /**
         *
         * anon 无需认证就可以访问
         * authc 必须认证了才能访问
         * user 必须拥有记住我才能访问
         * perms 拥有对某个资源的权限访问
         * role 拥有某个角色权限才能访问
         *
         *
         *
         */


        //添加shiro的内置过滤器链  需要传入一个map
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();


        //必须认证才能访问这两个资源 规则写在上面 已经测试生效了
        filterChainDefinitionMap.put("/user/add","authc");
        filterChainDefinitionMap.put("/user/update","authc");
        //授权  没有授权的话就会跳转到未授权界面  这个下面的意思就是说/user/add的路径只有你有user:add这个权限的人才能访问
        //顺序问题会影响这个代码 你要是把下面这行代码放在上面上两句话就不能拦截成功了  我们设置好了拦截规则 接下来就是认证了
        filterChainDefinitionMap.put("/user/add","perms[user:add]");
        filterChainDefinitionMap.put("/user/update","perms[user:update]");


        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);


        //设置未授权的跳转界面
        bean.setUnauthorizedUrl("/unauthorized");
        //当验证不通过时通过url请求跳转到我们自己写的登录页面
        bean.setLoginUrl("/toLogin");


        return bean;
    }
}