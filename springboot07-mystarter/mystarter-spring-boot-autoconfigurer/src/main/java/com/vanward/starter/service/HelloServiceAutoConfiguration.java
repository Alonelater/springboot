package com.vanward.starter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration//标注这个是一个配置类
@ConditionalOnWebApplication//标注这是web环境才生效
@EnableConfigurationProperties(HelloProperties.class)//让属性文件生效 所以我们可以直接用  相当于我们的属性文件上面的注解@ConfigurationProperties标注可以在这里直接从容器中拿到 我们就能直接使用@AutoWired使用了  然后将它交给我们的工具HelloService类进行使用 就不会造成HelloService空指针什么的了
public class HelloServiceAutoConfiguration {


    //直接获得HelloProperties
    @Autowired
    HelloProperties helloProperties;

    //我们需要一个HelloService 所以我们直接注入进来

    @Bean
    public HelloService helloService(){
        //其中 HelloService又需要一个HelloProperties 所以我们直接在上面增加一个属性文件生效的注解@EnableConfigurationProperties(HelloProperties.class)
        HelloService service = new HelloService();
        service.setHelloProperties(helloProperties);
        return service;
    }



    //上面该有的属性文件已经有了  接下来就是将这个我们自己学的自动配置类 交给springboot去管理 叫他帮我们自动装配 就要遵循规则 放在resources下面的META-INF下面的spring.factories
    //其实我们可以参考mybatis的启动器 因为他也是整个进去的
}