package com.vanward.starter.mystarterspringboottest.controller;


import com.vanward.starter.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HelloController {


    @Autowired
    private HelloService helloService;

    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello(){
        return helloService.sayHelloService("缪永金");

    }
}