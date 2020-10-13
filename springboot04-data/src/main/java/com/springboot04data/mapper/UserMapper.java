package com.springboot04data.mapper;

import com.springboot04data.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository//表示dao层的注解
@Mapper//这个注解表示了这个类就是mybatis的mapper类  或者在主启动类上面借@MapperScan("com.springboot04data.dao")也行
public interface UserMapper {
    List<User> queryList();
    User queryUserByName(String name);
    int addUser(User user);
    int updateUser(User user);
    int deleteUser(String name);
}
