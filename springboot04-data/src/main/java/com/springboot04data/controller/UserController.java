package com.springboot04data.controller;

import com.springboot04data.mapper.UserMapper;
import com.springboot04data.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class UserController {


    @Autowired
    private UserMapper userMapper;


    @RequestMapping("/list")
    public List<User> userList(){


        List<User> users = userMapper.queryList();
        for (User user : users) {
            System.out.println(user);
        }

        return users;
    }

    //既然查询可以，增删改也应该可以  有时间再试试

    //新增一个用户
    @GetMapping("/add")
    public String addUser(){
        //插入语句，注意时间问题

        userMapper.addUser(new User("小王","123",18));
        //查询
        return "addOk";
    }

    //修改用户信息
    @GetMapping("/update/{userName}")
    @ResponseBody
    public String updateUser(@PathVariable("userName") String userName){

        User user = userMapper.queryUserByName(userName);

        user.setPassword("147258");
        user.setAge(18);
        userMapper.updateUser(user);
        //查询
        return "updateOk";
    }

    //删除用户
    @GetMapping("/delete/{userName}")
    @ResponseBody
    public String delUser(@PathVariable("userName") String userName){
        userMapper.deleteUser(userName);
        //查询
        return "deleteOk";
    }


}
