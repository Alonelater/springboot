package com.springboot05security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RouterController {


    //配置路径路由
    @RequestMapping({"/","/index","index.html","/aaa"})
    public String index(){
        return "index";
    }


    //配置路径路由
    @RequestMapping("/level/{vip}/{id}")
    public String level1(@PathVariable("vip") Integer vip,@PathVariable("id") Integer id){
        String s = "views/level"+vip+"/leve11-"+id;
        return s;
    }


}
