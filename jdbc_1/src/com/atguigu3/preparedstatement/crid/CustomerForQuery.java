package com.atguigu3.preparedstatement.crid;

import com.atguigu3.bean.Customer;
import com.atguigu3.util.JDBCUtils;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.*;

/**
 * 针对于Customers表的查询操作
 *
 * @Author ylkstack
 * @Date 2021/10/20 21:23
 */
public class CustomerForQuery {
    @Test
    public void testQueryForCustomers() throws Exception {
        String sql = "select id,name,birth,email from customers where id = ?";
        Customer customer = queryForCustomers(sql, 13);
        System.out.println(customer);
        sql = "select name,email from customers where name = ?";
        Customer customers = queryForCustomers(sql, "周杰伦");
    }

    /**
     * 针对于customers表的通用的查询操作
     * @Date 2021/10/22 23:15
     * @return null
     */
    public Customer queryForCustomers(String sql,Object...args) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;//查询结果集
        try {
            //创建jdbc连接
            conn = JDBCUtils.getConnection();
            //预编译sql语句
            ps = conn.prepareStatement(sql);
            //遍历有几个形参args   填充占位符
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1,args[i]);
            }
            rs = ps.executeQuery();
            //获取结果集的元数据
            ResultSetMetaData rsmd = rs.getMetaData();
            //通过ResultSetMetaDate获取结果集中的列数
            int columnCount = rsmd.getColumnCount();
            if (rs.next()) {
                Customer cust = new Customer();//写这里合适，查到了再造对象，不循环
                //处理结果集一行数据中的每一个列
                for (int i = 0; i < columnCount; i++) {
                    //获取列值
                    Object columnValue = rs.getObject(i + 1);//没法强转，因为不知道要返回的类型

                    //获取每个列的列名
//                    String columnName = rsmd.getColumnName(i + 1);
                    String columnLabel = rsmd.getColumnLabel(i + 1);

                    //给cust对象指定的catalogName属性，赋值为columnValue,通过反射
                    Field field = Customer.class.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(cust,columnValue);
                }
                return cust;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, ps, rs);
        }
        return null;
    }

    @Test
    public void testQuery1(){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "select id,name,email,birth from customers where id = ?";
            ps = conn.prepareStatement(sql);

            //执行，并返回结果
            resultSet = ps.executeQuery();
            //处理结果集
            if (resultSet.next()) {//next判断结果集的下一条是否有数据，如果有数据返回true，指针下移。如果返回false，指针不会下移
                //获取当前这条数据的各个字段值
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String email = resultSet.getString(3);
                Date birth = resultSet.getDate(4);
                //处理数据
                //方式一：比较啰嗦一般不用
    //            System.out.println("id = " + id + ",name = " + name ",email = " + email + ",birth = " + birth);
                //方式二：封装到数组当中
                Object[] data = new Object[]{id, name, email, birth};
                //方式三：把数据封装为一个对象----对应customer类
                Customer customer = new Customer(id, name, email, birth);
                System.out.println(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            JDBCUtils.closeResource(conn,ps,resultSet);
        }

    }
}
