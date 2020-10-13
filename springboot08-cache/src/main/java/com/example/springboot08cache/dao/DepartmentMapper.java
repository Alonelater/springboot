package com.example.springboot08cache.dao;

import com.example.springboot08cache.pojo.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DepartmentMapper {
    @Select("SELECT * FROM department WHERE id = #{id}")
    Department getDeptById(Integer id);
}
