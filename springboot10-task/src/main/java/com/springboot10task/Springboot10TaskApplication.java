package com.springboot10task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync//开始异步任务的注解模式
@EnableScheduling//开始定时任务的注解模式
@SpringBootApplication
public class Springboot10TaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot10TaskApplication.class, args);
    }

}
