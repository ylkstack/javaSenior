package com.atguigu3.preparedstatement.crid;

import com.atguigu3.bean.Order;
import com.atguigu3.util.JDBCUtils;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.*;

/**
 * 针对于Order表的通用查询操作
 * @Description
 * @Author ylkstack
 * @Date 2021/10/23 19:30
 */
public class OrderForQuery {
    /*
     * 针对于表的字段名与类的属性名不同的情况
     * 1. 必须声明sql时，使用类的属性名来命名字段的别名
     * 2. 使用ResultSetMetaDate时，需要使用getColumnLabel()来替换getColumnName()
     *     获取列的别名即类的属性名
     *  说明，如果sql中没有给字段取别名，getColumnLabel()获取的就是列名。
     *
     */

    @Test
    public void testOrderForQuery() throws Exception {
        String sql = "select order_id orderId,order_name orderName,order_date orderDate from `order` where order_id = ?";
        Order order = orderForQuery(sql, 1);
        System.out.println(order);
    }
/**
 * 通用的针对order表的查询操作
 * @Date 2021/10/23 19:48
 * @return null
 */
    public Order orderForQuery(String sql,Object...args) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1,args[i]);
            }

            //执行,获取结果集
            rs = ps.executeQuery();
            //获取结果集的元数据
            ResultSetMetaData rsmd = rs.getMetaData();
            //获取列数
            int columnCount = rsmd.getColumnCount();
            if (rs.next()) {
                Order order = new Order();
                for (int i = 0; i < columnCount; i++) {
                    //获取每个列的列值:通过ResultSet   值是保存在结果集里的
                    Object columnValue = rs.getObject(i + 1);
                    //获取各个列的列名:通过ResultSetMetaDate   修饰的是存在元数据里的,如列数、列名
                    //获取列的列名：getColumnName()---不推荐使用
                    //获取列的别名：getColumnLabel()
//                    String columnName = rsmd.getColumnName(i + 1);
                    String columnLabel = rsmd.getColumnLabel(i + 1);
                    //通过反射，将对象指定名columnName的属性赋值为指定值columnValue
                    Field field = Order.class.getDeclaredField(columnLabel);
                    field.setAccessible(true);//保证属性是私有的也可以访问
                    field.set(order,columnValue);
                }
                return order;

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn,ps,rs);
        }
        return null;
    }



    @Test
    public void testQuery1() throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "select order_id,order_name,order_date from `order` where order_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setObject(1,1);

            rs = ps.executeQuery();
            if (rs.next()) {
                int id = (int) rs.getObject(1);
                String name = (String) rs.getObject(2);
                Date date = (Date) rs.getObject(3);

                Order order = new Order(id, name, date);
                System.out.println(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭资源操作
            JDBCUtils.closeResource(conn,ps,rs);
        }
    }

}
