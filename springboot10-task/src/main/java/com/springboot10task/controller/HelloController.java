package com.springboot10task.controller;

import com.springboot10task.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    AsyncService service;

    @RequestMapping("/hello")
    public String hello(){
        service.hello();
        return "hello";
    }
}
