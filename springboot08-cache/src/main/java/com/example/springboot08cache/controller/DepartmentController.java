package com.example.springboot08cache.controller;

import com.example.springboot08cache.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepartmentController {

    @Autowired
    DepartmentService deptService;
//
//    @GetMapping("/dept/{id}")
//    public Department getDept(@PathVariable("id") Integer id){
//        return deptService.getDeptById(id);
//    }
}
