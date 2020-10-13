package com.springboot06shiro.service;

import com.springboot06shiro.pojo.User;

public interface UserSevice {
    User queryUserByName(String name);
}
