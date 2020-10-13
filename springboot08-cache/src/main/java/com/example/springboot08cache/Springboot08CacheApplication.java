package com.example.springboot08cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class Springboot08CacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot08CacheApplication.class, args);
    }

}
