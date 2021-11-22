package com.atguigu.exer1;

import java.util.List;

/**
 * 定义一个测试类：
 * 创建 DAO 类的对象， 分别调用其 save、get、update、list、delete 方
 * 法来操作 User 对象，
 * 使用 Junit 单元测试类进行测试。
 *
 * @author: ylkstack  Email:158656887@qq.com
 * @Date: 2021/7/26 22:35
 */
public class DAOTets {
    public static void main(String[] args) {
        DAO<User> dao = new DAO<>();

        dao.save("1001", new User(1001,34,"周杰伦"));
        dao.save("1002", new User(1002,20,"昆凌"));
        dao.save("1003", new User(1003,25,"蔡依林"));

        //修改操作
        dao.update("1003", new User(1003,30,"方文山"));

        //删除操作
        dao.delete("1002");

        List<User> list = dao.list();
//        System.out.println(list);
        //jdk8 新特性 forEach() ....方法引用输出
        list.forEach(System.out::println);
    }
}
