package com.atguigu4.exer;

import com.atguigu3.util.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

/**
 * @Description
 * @Author ylkstack
 * @Date 2021/10/25 23:04
 * 课后练习1
 */
public class Exer1Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入第一个数字");
        int num1 = scanner.nextInt();
        System.out.print("请输入第二个数字");
        int num2 = scanner.nextInt();
        System.out.print(num1 + num2);
        System.out.println("测试");
    }


    @Test
    public void testInsert() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入用户名：");
        String name = scanner.next();
        System.out.print("请输入邮箱：");
        String email = scanner.next();
        System.out.print("请输入生日：");
        String birthday = scanner.next();//1992-09-08数据库内有隐式转换

        String sql = "insert into customers(name,email,birth)values(?,?,?)";
        int insertCount = update(sql, name, email, birthday);
        if (insertCount > 0) {
            System.out.println("添加成功");
        } else {
            System.out.println("添加失败");
        }

    }

    //通用的增删改操作
    public int update(String sql, Object ... args){//sql当中占位符的个数与可变形参的长度相同
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
            /*
             *ps.execute():
             * 如果执行的是查询操作，有返回结果，则此方法返回true;
             * 如果执行的是增、删、改操作，则返回false
             */
            //关于增删改操作的，方式一：
//            return ps.execute();
            //方式二：
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //5.资源的关闭
            JDBCUtils.closeResource(conn,ps);
        }
        return 0;
    }
}
