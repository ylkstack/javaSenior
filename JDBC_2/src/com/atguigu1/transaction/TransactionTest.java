package com.atguigu1.transaction;

import com.atguigu1.util.JDBCUtils;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.*;

/**
 * Transacntion的测试
 * @Author ylkstack
 * @Date 2021/10/31 20:19
 */
/*
 * 1、什么叫数据库事务？
 * 事务：一组逻辑操作单元,使数据从一种状态变换到另一种状态。
 *      >一组逻辑操作单元，一个或多个DML操作
 * 2、事务处理的原则:保证所有事务都作为一个工作单元来执行，即使出现了故障，
 * 都不能改变这种执行方式。当在一个事务中执行多个操作时，
 * 要么所有的事务都被提交(commit)，那么这些修改就永久地保存下来；
 * 要么数据库管理系统将放弃所作的所有修改，整个事务回滚(rollback)到最初状态。
 * 3、数据一旦提交，就不可回滚
 *      >DDL操作一旦执行，都会自动提交
 *      >DML默认情况下，一旦执行，就会自动提交。
 *          >我们可以通过set autocommit = false的方式取消DML操作的自动提交
 *      >默认在关闭连接时，会自动提交数据
 */
public class TransactionTest {
    //----------------未考虑数据库事务的转账操作------------------
    /*
     * 针对于数据表user_table来说，
     * AA用户给BB用户转账100
     *
     * update user_table set balance = balance - 100 where user = 'AA';
     * update user_table set balance = balance - 100 where user = 'BB';
     */
    @Test
    public void testUpdata() {
        String sql1 = "update user_table set balance = balance - 100 where user = ?";
        update(sql1, "AA");

        //模拟网络异常
        System.out.println(10 / 0);

        String sql2 = "update user_table set balance = balance + 100 where user = ?";
        update(sql2, "BB");

        System.out.println("转账成功");
    }

    //通用的增删改操作-----version  1.0
    public int update(String sql,Object ... args){//sql当中占位符的个数与可变形参的长度相同
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //1.获取数据库的连接
            conn = JDBCUtils.getConnection();
            //2.预编译sql语句，返回PreparedStatement的实例
            ps = conn.prepareStatement(sql);
            //3.填充占位符
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1,args[i]);//小心参数声明错误！sql从1开始，数组从0开始
            }
            //4.执行
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //5.资源的关闭
            JDBCUtils.closeResource(conn,ps);
        }
        return 0;
    }

    //***************考虑数据库事务的转账操作*****************
    @Test
    public void testUpdateWithTx() {//此处应抛出异常，不处理
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            System.out.println(conn.getAutoCommit());//结果：true默认自动提交
            //取消数据的自动提交功能
            conn.setAutoCommit(false);//调用方法取消自动提交
            String sql1 = "update user_table set balance = balance - 100 where user = ?";
            update(conn,sql1, "AA");

            //模拟网络异常
            System.out.println(10 / 0);

            String sql2 = "update user_table set balance = balance + 100 where user = ?";
            update(conn,sql2, "BB");

            System.out.println("转账成功");
            //提交数据
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            //修改其为自动提交数据
            //主要针对与使用数据库连接池的使用
            try {
                 conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            JDBCUtils.closeResource(conn,null);
        }
    }

    //通用的增删改操作-----version  2.0     （考虑数据库事务）
    public int update(Connection conn,String sql,Object ... args){//sql当中占位符的个数与可变形参的长度相同
        PreparedStatement ps = null;
        try {
            //1.预编译sql语句，返回PreparedStatement的实例
            ps = conn.prepareStatement(sql);
            //2.填充占位符
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1,args[i]);//小心参数声明错误！sql从1开始，数组从0开始
            }
            //3.执行
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //4.资源的关闭
            JDBCUtils.closeResource(null,ps);
        }
        return 0;
    }

    //******************************************
    @Test
    public void testTransactionSelect() throws Exception {
        //获取连接
        Connection conn = JDBCUtils.getConnection();
        //获取当前连接的隔离级别
        System.out.println(conn.getTransactionIsolation());
        //设置数据库的隔离级别
        conn.setTransactionIsolation(1);
        //取消自动提交数据
        conn.setAutoCommit(false);
        //创建sql预编译语句
        String sql = "select user,password,balance from user_table where user = ?";
        //调用sql查询方法
        User user = getInstance(conn, User.class, sql, "CC");
        System.out.println(user);
    }

    @Test
    public void testTransactionUpdate() throws Exception {
        //获取连接
        Connection conn = JDBCUtils.getConnection();
        //取消自动提交数据
        conn.setAutoCommit(false);

        String sql = "update user_table set balance = ? where user = ?";
        update(conn,sql,5000,"CC");

        Thread.sleep(15000);
        System.out.println("修改结束");

    }

    //通用的查询操作，用于返回数据表中的一条记录(version 2.0 考虑上事务)
    //泛型方法，返回值类型就是T类型的。
    public <T> T getInstance(Connection conn,Class<T> clazz, String sql,Object...args) {
        PreparedStatement ps = null;
        ResultSet rs = null;//查询结果集
        try {
            //创建jdbc连接
            conn = JDBCUtils.getConnection();
            //预编译sql语句
            ps = conn.prepareStatement(sql);
            //遍历有几个形参args..填充占位符
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1,args[i]);
            }
            rs = ps.executeQuery();
            //获取结果集的元数据
            ResultSetMetaData rsmd = rs.getMetaData();
            //通过ResultSetMetaDate获取结果集中的列数
            int columnCount = rsmd.getColumnCount();
            if (rs.next()) {
                T t = clazz.getDeclaredConstructor().newInstance();//通过反射动态获取对象
//处理结果集一行数据中的每一个列
                for (int i = 0; i < columnCount; i++) {
                    //获取列值
                    Object columnValue = rs.getObject(i + 1);//没法强转，因为不知道要返回的类型

                    //获取每个列的列名
//                    String columnName = rsmd.getColumnName(i + 1);
                    String columnLabel = rsmd.getColumnLabel(i + 1);

                    //给clazz对象指定的catalogLabel属性，赋值为columnValue,通过反射
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t,columnValue);
                }
                return t;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(null, ps, rs);
        }
        return null;
    }
}
