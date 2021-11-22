package com.atguigu2.DAO;

import com.atguigu2.bean.Customer;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

/**
 * 此接口用于规范针对于customers表的常用操作
 * @Author ylkstack
 * @Date 2021/11/2 0:26
 */
public interface CustomerDAO {
    /**
     * 将cust对象添加到数据库中
     * @Date 2021/11/2 0:30
     * @param conn
     * @param cust
     * @return void
     */
    void insert(Connection conn, Customer cust);
    /**
     * 针对指定的id，删除表中的一条记录
     * @Date 2021/11/2 0:31
     * @param conn
     * @param id 需要删除数据对应的id
     * @return void
     */
    void daleteById(Connection conn, int id);

    /**
     * 针对内存中的cust对象，去修改表中指定的记录
     * @Date 2021/11/2 0:33
     * @param conn
     * @param cust 
     * @return void
     */
    void update(Connection conn, Customer cust);

    /**
     * 根据指定id查询得到对应的Customer对象
     * @Date 2021/11/2 0:34
     * @param conn
     * @param id
     * @return Customer对象
     */
    Customer getCustomerById(Connection conn, int id);
    /**
     * 查询表中的所有记录构成的集合
     * @Date 2021/11/2 0:36
     * @param conn
     * @return java.util.List<com.atguigu2.bean.Customer>
     */
    List<Customer> getAll(Connection conn);
    /**
     * 返回数据表中的数据的条目数
     * @Date 2021/11/2 0:38
     * @param conn
     * @return long类型的条目数
     */
    Long getCount(Connection conn);
    /**
     * 返回数据表中最大的生日
     * @Date 2021/11/2 0:39
     * @param conn
     * @return java.util.Date
     */
    Date getMaxBirth(Connection conn);
}
