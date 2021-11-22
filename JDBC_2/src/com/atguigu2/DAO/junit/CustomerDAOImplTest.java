package com.atguigu2.DAO.junit;

import com.atguigu1.util.JDBCUtils;
import com.atguigu2.DAO.CustomerDAOImpl;
import com.atguigu2.bean.Customer;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

/**
 * @Author ylkstack
 * @Date 2021/11/2 22:18
 */
public class CustomerDAOImplTest {

    //创建CustomerDAOImpl的对象
     private CustomerDAOImpl dao = new CustomerDAOImpl();
    @org.junit.jupiter.api.Test
    void insert() {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            Customer cust = new Customer(1, "于小飞", "xiaofei@126.com", new Date(454776564456L));
            dao.insert(conn,cust);
            System.out.println("添加成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn,null);
        }
    }

    @org.junit.jupiter.api.Test
    void daleteById() {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            dao.daleteById(conn,13);
            System.out.println("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn,null);
        }
    }

    @org.junit.jupiter.api.Test
    void update() {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            Customer cust = new Customer(18, "贝多芬", "beiduofen@126.com", new Date(56884656514L));
            dao.update(conn,cust);
            System.out.println("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn,null);
        }
    }

    @org.junit.jupiter.api.Test
    void getCustomerById() {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            Customer cust = dao.getCustomerById(conn, 19);
            System.out.println(cust);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn,null);
        }
    }

    @org.junit.jupiter.api.Test
    void getAll() {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            List<Customer> list = dao.getAll(conn);
            list.forEach(System.out::println);
            System.out.println("");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn,null);
        }
    }

    @org.junit.jupiter.api.Test
    void getCount() {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            Long count = dao.getCount(conn);
            System.out.println("表中的记录数为：" + count);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn,null);
        }
    }

    @org.junit.jupiter.api.Test
    void getMaxBirth() {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            Date maxBirth = dao.getMaxBirth(conn);
            System.out.println("表中的最大生日为：" + maxBirth);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn,null);
        }
    }
}