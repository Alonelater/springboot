package com.springboot04data.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data//加载数据的get,set等等
@NoArgsConstructor//无参构造
@AllArgsConstructor//全部有参构造
public class User {

    private String name;
    private String password;
    private int age;

}
