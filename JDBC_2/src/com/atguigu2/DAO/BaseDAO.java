package com.atguigu2.DAO;

import com.atguigu1.util.JDBCUtils;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO:data(base) access object
 * 封装了针对于数据表的通用操作
 * @Author ylkstack
 * @Date 2021/11/1 23:35
 */
//因为不需要此类创建对象，所以声明为抽象类
public abstract class BaseDAO {

    /**
     * 通用的增删改操作-----version  2.0     （考虑数据库事务）
     * @Date 2021/11/1 23:57
     * @param conn
     * @param sql
     * @param args
     * @return int
     */
    //通用的增删改操作-----version  2.0     （考虑数据库事务）
    public int update(Connection conn, String sql, Object ... args){//sql当中占位符的个数与可变形参的长度相同
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

    /**
     * 通用的查询操作，用于返回数据表中的一条记录(version 2.0 考虑上事务)
     * @Date 2021/11/1 23:56
     * @param conn
     * @param clazz
     * @param sql
     * @param args
     * @return 泛型方法，返回值类型就是T类型的。
     */
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

    /**
     * 通用的查询操作
     * 用于返回数据表中的多条记录构成的集合(version 2.0,考虑上事务)
     * @Date 2021/10/25 0:52
     * @return list
     */

    /**
     * 通用的查询操作
     * 用于返回数据表中的多条记录构成的集合(version 2.0,考虑上事务)
     * @Date 2021/11/2 0:16
     * @param conn 数据库连接
     * @param clazz 数据库对应的类
     * @param sql   静态编译的sql语句
     * @param args  占位符填充
     * @return java.util.List<T>
     */
    public <T> List<T> getForList(Connection conn,Class<T> clazz, String sql, Object... args) {
        PreparedStatement ps = null;
        ResultSet rs = null;//查询结果集
        try {
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
            //创建集合对象
            ArrayList<T> list = new ArrayList<>();
            while (rs.next()) {
                T t = clazz.getDeclaredConstructor().newInstance();//通过反射动态获取对象
                //处理结果集一行数据中的每一个列：给t对象指定的属性赋值
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
                //处理完之后，把结果添加到list里
                list.add(t);
            }
            return list;//处理完之后如果没有数据，也是有List的只是list的size为0
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(null, ps, rs);
        }
        return null;
    }

    /**
     * 用于查询特殊值的通用方法（如：组函数 count(*)）
     * @Date 2021/11/2 0:13
     * @param conn 连接
     * @param sql 预编译的sql语句
     * @param args 占位符填充
     * @return E
     */
    public <E> E getValue(Connection conn,String sql,Object...args){
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1,args[i]);
            }
            resultSet = ps.executeQuery();
            if (resultSet.next()) {
               return (E) resultSet.getObject(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(null,ps,resultSet);
        }
        return null;
    }
}
