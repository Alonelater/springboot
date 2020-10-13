package com.springboot01;

import com.springboot01.pojo.Dog;
import com.springboot01.pojo.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Springboot01ApplicationTests {
    @Autowired
    Dog dog;//将狗狗自动注入进来
    @Autowired
    Person person;
    @Test
    void contextLoads() {
        System.out.println(dog);
        System.out.println(person);
    }
}
