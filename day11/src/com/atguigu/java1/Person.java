package com.atguigu.java1;

import java.io.Serializable;

/**
 * 提供结构丰富的Person类----供反射测试用
 * @author: ylkstack  Email:158656887@qq.com
 * @Date: 2021/8/14 21:20
 */
@MyAnnotation(value = "hello")//重新赋值为hi
public class Person extends Creature<String> implements Comparable<String>,MyInterface{
    //提供不同权限的属性
    private String name;
    int age;
    public int id;

    //提供不同权限的构造器
    public Person() {}

    @MyAnnotation(value = "abc")//重新赋值
    private Person(String name) {
        this.name = name;
    }

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    //提供不同属性的方法
    @MyAnnotation//使用默认值
    private String show(String nation) {
        System.out.println("我的国籍是：" + nation);
        return nation;
    }

    public String dieplay(String insterests) throws NullPointerException,ClassCastException{
        return insterests;
    }
    @Override
    public void info() {
        System.out.println("我是一个人");
    }

    @Override
    public int compareTo(String o) {
        return 0;
    }

    private static void showDesc() {
        System.out.println("我是一个可爱的人");
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", id=" + id +
                '}';
    }
}
