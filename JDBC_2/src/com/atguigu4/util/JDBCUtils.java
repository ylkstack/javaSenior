package com.atguigu4.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.apache.commons.dbutils.DbUtils;

import javax.sql.DataSource;
import java.io.*;
import java.sql.*;
import java.util.Properties;

/**
 * 数据库连接池工具类
 * @Author ylkstack
 * @Date 2021/11/7 19:58
 */
public class JDBCUtils {

    /**
     * 数据库链接操作
     *
     * @param
     * @return null
     * @Description
     * @Date 2021/10/20 20:12
     */
    public static Connection getConnection1() throws Exception {
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
     * 关闭资源操作
     * @Date 2021/11/13 23:48
     * @param conn 数据库连接
     * @param ps preparedStatement SQL预编译语句
     * @param rs resultSet 结果集对象
     * @return void
     */
    public static void closeResource(Connection conn, Statement ps, ResultSet rs){
        try {
            if(ps != null)
                ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(rs != null)
                rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用dbutils.jar中提供的DbUtils工具类，实现资源的关闭
     * @Date 2021/11/13 23:50
     * @param conn 数据库连接
     * @param ps preparedStatement SQL预编译语句
     * @param rs  resultSet 结果集对象
     * @return void
     */
    public static void closeResource1(Connection conn, Statement ps, ResultSet rs){
        //方式一：
//        try {
//            if(ps != null)
//                ps.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        try {
//            if(conn != null)
//                conn.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        try {
//            if(rs != null)
//                rs.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        //方式二：
        DbUtils.closeQuietly(conn);
        DbUtils.closeQuietly(ps);
        DbUtils.closeQuietly(rs);
    }

    //连接数据库连接池对象需要先创建（xml文件：c3p0-config.xml）
    private static ComboPooledDataSource cpds = new ComboPooledDataSource("helloc3p0");
    /**
     * 使用C3P0的数据库连接池技术
     * @Date 2021/11/7 19:59
     * @return 返回一个连接
     */
    public static Connection getConnection() throws SQLException {
        Connection conn = cpds.getConnection();
        return conn;
    }

    /**
     * 使用DBCP数据库连接池技术获取数据库连接
     *
     * @Date 2021/11/7 23:10
     * @return java.sql.Connection
     */
    //创建一个DBCP数据库连接池
    private static DataSource source;
    //创建静态代码块比较方便
    static{
        try {
            //创建配置文件对象
            Properties properties = new Properties();
            //获取流的两种方式
            Properties pros = new Properties();
            FileInputStream is = new FileInputStream(new File("src/dbcp.properties"));
            //加载对象流
            //创建一个DBCP数据库连接池
            source = BasicDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection2() throws Exception {
        Connection conn = source.getConnection();

        return conn;
    }

    /**
     * 使用Druid数据库连接池技术
     */
    //创建一个静态的数据库连接池
    private static DataSource source1;
    //创建静态代码块，用于放置连接代码
    static{
        try {
            //创建配置文件对象
            Properties properties = new Properties();
            //读取配置文件
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("druid.properties");
            //加载流
            properties.load(is);
            source1 = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Druid数据库连接池技术
     * @Date 2021/11/9 23:09
     * @param
     * @return java.sql.Connection
     */
    public static Connection getConnection3() throws SQLException {
        Connection conn = source1.getConnection();
        return conn;
    }

}
