package com.atguigu4.connection;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

/**
 * @Author ylkstack
 * @Date 2021/11/8 22:29
 */
public class DruidTest {
    @Test
    public void getConnection() throws Exception {
        //创建配置文件对象
        Properties properties = new Properties();
        //读取配置文件
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("druid.properties");
        //加载流
        properties.load(is);
        DataSource source = DruidDataSourceFactory.createDataSource(properties);
        Connection conn = source.getConnection();
        System.out.println(conn);
    }

}
