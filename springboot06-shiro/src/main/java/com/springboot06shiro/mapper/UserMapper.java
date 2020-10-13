package com.springboot06shiro.mapper;

import com.springboot06shiro.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper {
    List<User> queryList();
    User queryUserByName(String name);
    int addUser(User user);
    int updateUser(User user);
    int deleteUser(String name);
}
