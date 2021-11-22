package com.atguigu.java;

/**
 * jdk9新特性：
 *
 * @author: ylkstack  Email:158656887@qq.com
 * @Date: 2021/8/29 20:19
 */
public interface MyInterface {
    //定义一个抽象想法
    void methodAbstrace();

    static void methodStatic() {
        System.out.println("我是接口中的静态方法");
    }

    //此处default是关键字，不是权限
    default void methodDefault() {
        System.out.println("我是接口中的默认方法");
    }

    //定一个私有方法（JDK 9以后版本允许接口中定义私有的方法）
    private void methodPrivate() {
        System.out.println("我是接口中的私有方法");
    }

}
