package com.atguigu5.dbutils;

import com.atguigu2.bean.Customer;
import com.atguigu4.util.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.*;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Handler;

/**
 * commons-dbutils 是 Apache 组织提供的一个开源 JDBC工具类库，封装了针对于数据库的增删改查操作
 *
 * @Author ylkstack
 * @Date 2021/11/10 23:09
 */
public class QueryRunnerTest {
    @Test
    public void testInsert() {
        Connection conn = null;
        try {
            QueryRunner runner = new QueryRunner();
            conn = JDBCUtils.getConnection3();
            String sql = "insert into customers(name,email,birth)value(?,?,?)";
            //此update操作有返回值，返回一个int数字，用于记录添加的次数
            int insertCount = runner.update(conn, sql, "蔡徐坤", "caixukun@126.com", "1997-09-08");
            //记录添加的条目数
            System.out.println("添加了" + insertCount + "条记录");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn,null);
        }
    }

    //测试查询
    /*
     * BeanHander:是ResultSetHandler接口的实现类，用于封装表中的一条记录。
     */
    @Test
    public void testQuery1(){
        Connection conn = null;
        try {
            //QueryRunner对象
            QueryRunner runner = new QueryRunner();
            //创建连接
            conn = JDBCUtils.getConnection3();
            String sql = "select id,name,email,birth from customers where id = ?";
            //创建结果集handler
            BeanHandler<Customer> handler = new BeanHandler<>(Customer.class);
            //要查询数据的结果
            Customer customer = runner.query(conn, sql, handler, 23);
            System.out.println(customer);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn,null);
        }
    }
    //查询多条记录
    @Test
    public void testQuery2() throws SQLException {
        Connection conn = null;
        try {
            //QueryRunner对象
            QueryRunner runner = new QueryRunner();
            //创建连接，调用Druid(德鲁伊数据库连接池)
            conn = JDBCUtils.getConnection3();
            String sql = "select id,name,email,birth from customers where id < ?";
            //创建结果集handler
            BeanListHandler<Customer> handler = new BeanListHandler<>(Customer.class);
            //要查询数据的结果
            List<Customer> list = runner.query(conn, sql, handler, 23);
            //list集合调用foreach（增强for循环来变量实现查询）
            list.forEach(System.out::println);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn,null);
        }

    }
    /*
     * MapHander:是ResultSetHandler接口的实现类，用于封装表中的一条记录。
     * 将字段及相应字段的值作为map中的key和value
     */
    @Test
    public void testQuery3(){
        Connection conn = null;
        try {
            //QueryRunner对象
            QueryRunner runner = new QueryRunner();
            //创建连接
            conn = JDBCUtils.getConnection3();
            String sql = "select id,name,email,birth from customers where id = ?";
            //创建结果集handler
            MapHandler handler = new MapHandler();
            //要查询数据的结果
            Map<String, Object> map = runner.query(conn, sql, handler, 23);
            System.out.println(map);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn,null);
        }
    }
    /*
     * MapListHander:是ResultSetHandler接口的实现类，用于封装表中的多条记录。
     * 将字段及相应字段的值作为map中的key和value,将这些map添加到List中
     */
    @Test
    public void testQuery4(){
        Connection conn = null;
        try {
            //QueryRunner对象
            QueryRunner runner = new QueryRunner();
            //创建连接
            conn = JDBCUtils.getConnection3();
            String sql = "select id,name,email,birth from customers where id < ?";
            //创建结果集handler
            MapListHandler handler = new MapListHandler();
            //要查询数据的结果
            List<Map<String, Object>> list = runner.query(conn, sql, handler, 23);
            list.forEach(System.out::println);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn,null);
        }
    }

    //查询有多少条数据
    @Test
    public void testQuery5(){
        Connection conn = null;
        try {
            //QueryRunner对象
            QueryRunner runner = new QueryRunner();
            //创建连接
            conn = JDBCUtils.getConnection3();
            //预编译sql语句，查询表中有多少条语句
            String sql = "select count(*) from customers";
            //创建结果集handler
            ScalarHandler handler = new ScalarHandler();
            //要查询数据的结果,Long类型的，强转结果
            Long count = (Long) runner.query(conn, sql, handler);
            System.out.println(count);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn,null);
        }
    }
    /*
     *ScalarHandler:用于查询特殊值
     */
    @Test
    public void testQuery6(){
        Connection conn = null;
        try {
            //QueryRunner对象
            QueryRunner runner = new QueryRunner();
            //创建连接
            conn = JDBCUtils.getConnection3();
            //预编译sql语句，查询表中有多少条语句
            String sql = "select max(birth) from customers";
            //创建结果集handler
            ScalarHandler handler = new ScalarHandler();
            //要查询数据的结果,Long类型的，强转结果
            Date maxBirth = (Date) runner.query(conn, sql, handler);
            System.out.println(maxBirth);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn,null);
        }
    }

    /*
     * 自定义ResultSetHandler的实现类
     */
    @Test
    public void testQuery7(){
        Connection conn = null;
        try {
            //QueryRunner对象
            QueryRunner runner = new QueryRunner();
            //创建连接，调用Druid(德鲁伊数据库连接池)
            conn = JDBCUtils.getConnection3();
            String sql = "select id,name,email,birth from customers where id = ?";
            //创建结果集handler
            ResultSetHandler<Customer> handler = new ResultSetHandler<Customer>() {
                @Override
                public Customer handle(ResultSet rs) throws SQLException {
                    //调用自定义结果集时，返回的结果为此方法内的返回值
//                    System.out.println("handle");
//                    return null;
//                    return new Customer(12, "成龙", "Jacky@126.com", new Date(4556845457545L));
                    if (rs.next()) {
                        int id = rs.getInt("id");
                        String name = rs.getString("name");
                        String email = rs.getString("email");
                        java.sql.Date birth = rs.getDate("birth");
                        Customer customer = new Customer(id, name, email, birth);
                        return customer;
                    }
                    return null;
                }
            };
            //要查询数据的结果
            Customer customer = runner.query(conn, sql, handler, 23);
            System.out.println(customer);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn,null);
        }
    }
}
