package com.atguigu.statement.crud;

import com.atguigu3.util.JDBCUtils;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Scanner;

/**
 * 演示使用PreparedStatement 替换Statement 解决SQL注入问题
 * @Description
 * @Author ylkstack
 * @Date 2021/10/25 22:25
 *
 * 除了解决Statement的拼串、sql注入问题之外，PrepareStatement 还有哪些好处呢？
 * 1.PrepareStatement操作Blob的数据，而Statement做不到
 * 2.PrepareStatement可以实现更高效的批量操作
 *
 */
public class PreparedStatementTest {
    @Test
    public void testLogin() {
        Scanner scan = new Scanner(System.in);

        System.out.print("用户名：");
        String userName = scan.nextLine();
        System.out.print("密   码：");
        String password = scan.nextLine();

        // SELECT user,password FROM user_table WHERE USER = '1' or ' AND PASSWORD = '
        // ='1' or '1' = '1';
        String sql = "SELECT user,password FROM user_table WHERE user = ?  and password = ?";
        User user = getInstance(User.class,sql);
        if (user != null) {
            System.out.println("登陆成功!");
        } else {
            System.out.println("用户名或密码错误！");
        }
    }

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
                ps.setObject(i + 1, args[i]);
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
                    field.set(t, columnValue);
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
