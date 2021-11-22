package com.atguigu4.exer;

import com.atguigu3.util.JDBCUtils;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Scanner;

/**
 * @Description
 * @Author ylkstack
 * @Date 2021/10/28 0:01
 */
//课后练习2：
public class Exer2Test {
    //问题1：向studenttest表中添加一条记录
    /*
     *Type:
    IDCard:
    ExamCard:
    StudentName:
    Location:
    Grade:
     */
    @Test
    public void testInsert() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("四级/六级：");
        int type = scanner.nextInt();
        System.out.print("身份证号：");
        String IDCard = scanner.next();
        System.out.print("准考证号：");
        String examCard = scanner.next();
        System.out.print("学生姓名:");
        String studentName = scanner.next();
        System.out.print("区域:");
        String location = scanner.next();
        System.out.print("成绩：");
        int grade = scanner.nextInt();

        String sql = "insert into studenttest(`Type`,`IDCard`,`ExamCard`,`StudentName`,`Location`,`Grade`)" +
                " values(?,?,?,?,?,?)";
        int insertCount = update(sql, type, IDCard, examCard, studentName, location, grade);

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

    //问题2：根据身份证号或准考正好查询学生成绩信息
    @Test
    public void queryWithIDCardOrExamCard() {
        System.out.println("请选择您要输入的类型");
        System.out.println("a：准考证号");
        System.out.println("b:身份证号");
        Scanner scanner = new Scanner(System.in);
        String selection = scanner.next();
        if ("a".equalsIgnoreCase(selection)) {
            System.out.println("请输入准考证号：");
            String examCard = scanner.next();
            String sql = "select FlowID flowID,Type type,IDCard,ExamCard examCard,StudentName studentName,Location location,Grade grade " +
                    "from studenttest where examCard = ?";
            Student student = getInstance(Student.class,sql,examCard);
            if (student != null) {
                System.out.println(student);
            } else {
                System.out.println("输入的准考证号有误！");
            }
            System.out.println(student);

        } else if ("b".equalsIgnoreCase(selection)) {
            System.out.print("请输入身份证号：");
            String IDCard = scanner.next();
            String sql = "select FlowID flowID,Type type,IDCard,ExamCard examCard,StudentName studentName,Location location,Grade grade " +
                    "from studenttest where IDCard = ?";
            Student student = getInstance(Student.class,sql,IDCard);
            if (student != null) {
                System.out.println(student);
            } else {
                System.out.println("输入的身份证号有误！");
            }
            System.out.println(student);
        } else {
            System.out.println("您输入的有误，请重新进入程序。");
        }

    }

    /**
     * 查询一条数据
     * @Date 2021/10/28 23:43
     * @param clazz
     * @param sql
     * @param args
     * @return T
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

    //问题3：删除指定的学生信息
    @Test
    public void testDeleteByExamCard() {
        System.out.println("请输入学生的学号:");
        Scanner scanner = new Scanner(System.in);
        String examCard = scanner.next();
        //查询指定准考证号的学生
        String sql = "select FlowID flowID,Type type,IDCard,ExamCard examCard,StudentName studentName,Location location,Grade grade " +
                "from studenttest where examCard = ?";
        Student student = getInstance(Student.class, sql, examCard);
        if (student == null) {
            System.out.println("查无此人，请重新输入");
        } else {
            String sql1 = "delete from examstudent where examCard = ?";
            int deleteCount = update(sql1, examCard);
            if (deleteCount > 0) {
                System.out.println("删除成功");
            }
        }
    }

    //优化问题3以后的操作
    @Test
    public void testDeleteByExamCard1() {
        System.out.println("请输入学生的学号:");
        Scanner scanner = new Scanner(System.in);
        String examCard = scanner.next();
        String sql = "delete from examstudent where examCard = ?";
        int deleteCount = update(sql, examCard);
        if (deleteCount > 0) {
            System.out.println("删除成功");
        } else {
            System.out.println("查无此人，请重新输入");
        }
    }

}
