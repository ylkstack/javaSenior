package com.atguigu5.blob;

import com.atguigu3.bean.Customer;
import com.atguigu3.util.JDBCUtils;
import org.hamcrest.core.Is;
import org.junit.Test;

import java.io.*;
import java.sql.*;

/**
 * 测试使用PreparedStatement操作Blob类型的数据
 * @Author ylkstack
 * @Date 2021/10/30 0:09
 */
public class BlobTest {
    //向数据表customers中插入Blob类型的字段
    @Test
    public void testInsert() throws Exception {
        //获取链接
        Connection connection = JDBCUtils.getConnection();
        //预编译sql语句，并填充占位符
        String sql = "insert into customers(name,email,birth,photo)values(?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        //为占位符赋值
        preparedStatement.setObject(1,"袁浩");
        preparedStatement.setObject(2,"email");
        preparedStatement.setObject(3,"1992-09-08");
        FileInputStream is = new FileInputStream(new File("girl.jpg"));
        preparedStatement.setBlob(4, is);

        preparedStatement.execute();
        JDBCUtils.closeResource(connection,preparedStatement);
    }

    //查询数据表customers中Blob类型的字段
    @Test
    public void testQuery() throws Exception {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        InputStream is = null;
        FileOutputStream fileOutputStream = null;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "select id,name,email,birth,photo from customers where id = ?";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,20);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
    //            //方式一：
    //            int id = resultSet.getInt(1);
    //            String name = resultSet.getString(2);
    //            String email = resultSet.getString(3);
    //            Date birth = resultSet.getDate(4);
                //方式二：
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                Date birth = resultSet.getDate("birth");

                Customer customer = new Customer(id, name, email, birth);
                System.out.println(customer);

                //将Blob类型的字段下载下来，以文件方式保存在本地
                Blob photo = resultSet.getBlob("photo");
                is = photo.getBinaryStream();
                //获取输出流用来接收Bolo数据
                fileOutputStream = new FileOutputStream("IMG_20191202_234010.jpg.JPG.jpg");
                byte[] buffer = new byte[1024];
                int len;
                while ((len = is.read(buffer)) != -1) {
                    fileOutputStream.write(buffer,0,len);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                if (is != null)
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fileOutputStream != null)
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            JDBCUtils.closeResource(conn,preparedStatement,resultSet);
        }

    }
}
