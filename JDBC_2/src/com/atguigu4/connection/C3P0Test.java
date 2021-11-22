package com.atguigu4.connection;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;
import org.junit.Test;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @Author ylkstack
 * @Date 2021/11/6 23:39
 */
public class C3P0Test {
    //方式一：硬编码连接方式
    @Test
    public void testGetConnection() throws Exception {
        //获取c3p0数据库连接池
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        cpds.setDriverClass( "com.mysql.jdbc.Driver" ); //loads the jdbc driver
        cpds.setJdbcUrl( "jdbc:mysql://localhost:3306/test" );
        cpds.setUser("root");
        cpds.setPassword("ding1352");
        //通过设置相关的参数，对数据库连接池进行管理
        //设置初始时数据连接池中的连接数
        cpds.setInitialPoolSize(10);
        //获取数据库链接
        Connection conn = cpds.getConnection();
        System.out.println(conn);

//        //销毁c3p0数据库连接池，一般不会使用
//        DataSources.destroy( cpds );

    }

    //方式二：使用配置文件的方式
    @Test
    public void testGetConnection1() throws SQLException {
        ComboPooledDataSource cpds = new ComboPooledDataSource("helloc3p0");
        Connection conn = cpds.getConnection();
        System.out.println(conn);
    }
}
