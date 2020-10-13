package com.springboot06shiro;

import com.springboot06shiro.pojo.User;
import com.springboot06shiro.service.UserSevice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Springboot06ShiroApplicationTests {


    @Autowired
    UserSevice userSevice;
    @Test
    void contextLoads() {
        User user = userSevice.queryUserByName("1");
        System.out.println(user);
    }

}
