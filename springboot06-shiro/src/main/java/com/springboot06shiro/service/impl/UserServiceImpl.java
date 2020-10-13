package com.springboot06shiro.service.impl;

import com.springboot06shiro.mapper.UserMapper;
import com.springboot06shiro.pojo.User;
import com.springboot06shiro.service.UserSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserSevice {

    @Autowired
    UserMapper userMapper;

    @Override
    public User queryUserByName(String name) {
        return userMapper.queryUserByName(name);
    }
}
