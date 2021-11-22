package com.atguigu.java;

import java.io.Serializable;

/**
 * 自定义序列化类
 * Person需要满足如下的要求，方可序列化
 *
 * 1.需要实现接口：Serializable -------- Serializable算是一个标识，凡是有这个标识的都是可序列化的
 * 2.需要当前类提供一个全局常量：serialVersionUID
 * 3.除了当前Person类需要实现Serializable接口之外，还必须保证其内部所有属性也必须是可序列化的
 *   （默认情况下，基本数据类型可序列化）
 * @author: ylkstack  Email:158656887@qq.com
 * @Date: 2021/8/2 23:26
 */
public class Person implements Serializable {
    public static final long serialVersionUID = 424548545474L;//序列版本号
    private String name;//不支持序列化static修饰的成员变量。。因为static是归类调用。不属于对象本身
    private int age;//不支持序列化 transient修饰的成员变量。transient的意思就是不让序列化属性
    private int id;
    private Account acct;
    public Person() {
    }


    public Person(String name, int age, int id, Account acct) {
        this.name = name;
        this.age = age;
        this.id = id;
        this.acct = acct;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Account getAcct() {
        return acct;
    }

    public void setAcct(Account acct) {
        this.acct = acct;
    }

    public Person(String name, int age, int id) {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", id=" + id +
                ", acct=" + acct +
                '}';
    }

}
class Account implements Serializable{
    //显式定义UID版本号
    public static final long serialVersionUID = 486535242165L;
    private double balance;

    public Account() {
    }

    public Account(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "balance=" + balance +
                '}';
    }
}
