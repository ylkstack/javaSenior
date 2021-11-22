package com.atguigu1.util;

import org.junit.Test;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

/**
 * 操作数据库的工具类
 * @Description
 * @Author ylkstack
 * @Date 2021/10/20 19:28
 */
public class JDBCUtils {


    
    public static void main(String[] args) {
        System.out.println("args = " + Arrays.deepToString(args));
        System.out.println("JDBCUtils.main");
        String[] arr = new String[]{"tao", "jerry", "丁鑫", "曦玥"};

        ArrayList<Object> list = new ArrayList<>();
        list.add(123);
        list.add(345);
        list.add(567);
    }

    /**
     * 数据库链接操作
     *
     * @param
     * @return null
     * @Description
     * @Date 2021/10/20 20:12
     */
    public static Connection getConnection() throws Exception {
//1.读取配置文件中的4个基本信息
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");

        Properties pros = new Properties();
        pros.load(is);


        String user = pros.getProperty("user");
        String password = pros.getProperty("password");
        String url = pros.getProperty("url");
        String driverClass = pros.getProperty("driverClass");

        //2.加载驱动。8.0驱动不需要加载，自动加载的
//        Class.forName(driverClass);

        //3.获取连接
        Connection conn = DriverManager.getConnection(url, user, password);
        return conn;
    }

    /**
     * 关闭连接和Statement的操作
     *
     * @param
     * @return null
     * @Description
     * @Date 2021/10/20 20:17
     */
    public static void closeResource(Connection conn, Statement ps) {
        try {
            if (ps != null)
                ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * //TODO 关闭资源的操作
     * @Date 2021/10/20 22:07
     * @return null
     */
    public static void closeResource(Connection conn, Statement ps, ResultSet rs) {
        try {
            if (ps != null)
                ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (rs != null)
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
