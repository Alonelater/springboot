package com.springboot04data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/jdbc")
public class JDBCController {


    @Autowired
    JdbcTemplate jdbcTemplate;

    //查询employee表中所有数据
    //List 中的1个 Map 对应数据库的 1行数据
    //Map 中的 key 对应数据库的字段名，value 对应数据库的字段值

    @RequestMapping("/list")
    @ResponseBody
    public List<Map<String, Object>> userList(){
        String sql = "select * from user";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        return maps;
    }

    //既然查询可以，增删改也应该可以  有时间再试试

    //新增一个用户
    @GetMapping("/add")
    @ResponseBody
    public String addUser(){
        //插入语句，注意时间问题
        String sql = "insert into user(name,password,age)" +
                " values ('小喵','123456',1)";
        jdbcTemplate.update(sql);
        //查询
        return "addOk";
    }

    //修改用户信息
    @GetMapping("/update/{userName}")
    @ResponseBody
    public String updateUser(@PathVariable("userName") String userName){
        //插入语句
        String sql = "update user set password=? where name=?";
        //数据
        Object[] objects = new Object[2];
        objects[0] = "654321";
        objects[1] = userName;
        jdbcTemplate.update(sql,objects);
        //查询
        return "updateOk";
    }

    //删除用户
    @GetMapping("/delete/{userName}")
    @ResponseBody
    public String delUser(@PathVariable("userName") String userName){
        //插入语句
        String sql = "delete from user where name=?";
        jdbcTemplate.update(sql,userName);
        //查询
        return "deleteOk";
    }

}
