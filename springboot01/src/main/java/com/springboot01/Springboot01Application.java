package com.springboot01;

import com.springboot01.pojo.Dog;
import com.springboot01.pojo.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Springboot01Application {

    @Autowired
    private static Dog dog;

    @Autowired
    private static Person person;

    public static void main(String[] args) {
        //以为是启动了一个方法，没想到启动了一个服务

        SpringApplication.run(Springboot01Application.class, args);

        System.out.println(dog);
        System.out.println(person);

    }
}
