package com.atguigu4.connection;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @Author ylkstack
 * @Date 2021/11/7 20:37
 */
public class DBCPTest {
    @Test
    /**
     * 测试DBCP的数据库连接池技术
     *
     * @Date 2021/11/7 20:39
     */
    //方式一，不推荐。
    public void testGetConnection() throws SQLException {
        //创建DBCP的数据库连接池
        BasicDataSource source = new BasicDataSource();

        //设置基本信息
        source.setDriverClassName("com.mysql.cj.jdbc.Driver");
        source.setUrl("jdbc:mysql://localhost:3306/test");
        source.setUsername("root");
        source.setPassword("ding1352");

        //还可以设置其他的涉及数据库连接池管理的相关属性
        source.setInitialSize(10);
        source.setMaxActive(10);
        //...
        Connection conn = source.getConnection();
        System.out.println(conn);
    }

    //方式二：推荐，使用配置文件
    @Test
    public void testGetConnection1() throws Exception{
        //创建配置文件对象
        Properties properties = new Properties();
        //获取流的两种方式
//        //方式1：使用类的加载器ClassLoader
//        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("dbcp.properties");
        //方式2：
        FileInputStream is = new FileInputStream(new File("src/dbcp.properties"));
        //加载对象流
        properties.load(is);
        DataSource source = BasicDataSourceFactory.createDataSource(properties);
        Connection conn = source.getConnection();
        System.out.println(conn);
    }

}
