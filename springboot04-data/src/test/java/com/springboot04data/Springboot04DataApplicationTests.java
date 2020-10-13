package com.springboot04data;

import com.alibaba.druid.pool.DruidDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
class Springboot04DataApplicationTests {


    @Autowired
    DataSource dataSource;

    @Test
    void contextLoads() throws SQLException {
        System.out.println(dataSource.getClass());
        Connection connection = dataSource.getConnection();
        DruidDataSource dataSource1 = (DruidDataSource)dataSource;
        System.out.println("druidDataSource 数据源最大连接数：" + dataSource1.getMaxActive());
        System.out.println("druidDataSource 数据源初始化连接数：" + dataSource1.getInitialSize());

        System.out.println(connection);
    }

}
