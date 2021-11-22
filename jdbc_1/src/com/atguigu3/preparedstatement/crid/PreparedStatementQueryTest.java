package com.atguigu3.preparedstatement.crid;

import com.atguigu3.bean.Customer;
import com.atguigu3.bean.Order;
import com.atguigu3.util.JDBCUtils;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 * 使用PreparedStatement实现针对不同表的通用查询操作
 * @Description
 * @Author ylkstack
 * @Date 2021/10/24 23:41
 */
public class PreparedStatementQueryTest {

    @Test
    public void testGetForList() {
        String sql1 = "select id,name,email from customers where id < ?";
        List<Customer> list1 = getForList(Customer.class, sql1,12);
        list1.forEach(System.out::println);


        String sql = "select order_id orderId,order_name orderName,order_date orderDate from `order` where order_id < ?";
        List<Order> list = getForList(Order.class, sql, 5);
        list.forEach(System.out::println);
    }

    /**
     * 查询多条数据
     * @Date 2021/10/25 0:52
     * @return list
     */

    public <T> List<T>  getForList(Class<T> clazz, String sql, Object... args) {
        Connection conn = null;
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
            JDBCUtils.closeResource(conn, ps, rs);
        }
        return null;
    }

    @Test
    public void testGetInstance() {
        String sql = "select id,name,email from customers where id = ?";
        Customer customer = getInstance(Customer.class, sql, 12);
        System.out.println(customer);

        String sql1 = "select order_id orderId,order_name orderName,order_date orderDate from `order` where order_id = ?";
        Order order = getInstance(Order.class, sql1, 2);
        System.out.println(order);
    }

    /**
     * 查询一条数据
     * @Date 2021/10/28 23:33
     * @return null
     */
    //泛型方法，返回值类型就是T类型的。
    public <T> T getInstance(Class<T> clazz, String sql,Object...args) {
        Connection conn = null;
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
            JDBCUtils.closeResource(conn, ps, rs);
        }
        return null;
    }
}
