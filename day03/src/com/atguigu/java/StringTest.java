package com.atguigu.java;

import org.junit.Test;

/**
 * String的使用
 *
 *
 * @author: ylkstack  Email:158656887@qq.com
 * @Date: 2021/6/19 22:51
 */
public class StringTest {//able  是可...的，能够的

    /*
    结论：
    1.常量与常量的拼接结果再常量池。且常量池中不会存在相同内容的常量
    2.只要其中有一个变量，结果就在堆中。
    3.如果拼接的结果调用intern()方法，返回值就在常量池中

     */
    @Test
    public void test4(){
        String s1 = "javaEEhadoop";
        String s2 = "javaEE";
        String s3 = s2 +"hadoop";
        System.out.println(s1 == s3);//false

        final String s4 = "javaEE";//final修饰，表示常量
        String s5 = s4 +"hadoop";//所以s5的结果也是在常量池
        System.out.println(s1 == s5);//所以结果为：true. 因为是常量和常量链接
    }
    @Test
    public void test3(){
        String s1 = "javaEE";
        String s2 = "hadoop";

        String s3 = "javaEEhadoop";
        String s4 = "javaEE" + "hadoop";
        String s5 = s1 + "hadoop";
        String s6 = "javaEE" + s2;
        String s7 = (s1 + s2).intern();

        System.out.println(s3 == s4);//true
        System.out.println(s3 == s5);//false
        System.out.println(s3 == s6);//false
        System.out.println(s3 == s7);//false
        System.out.println(s5 == s6);//false
        System.out.println(s5 == s7);//false
        System.out.println(s6 == s7);//false

        String s8 = s5.intern();//intern返回字符串对象的规范化表示形式
        //返回值得到的s8使用的常量值中已经存在的"javaEEhadoop"
        System.out.println(s3 == s8);//true
    }
    /*
    String 的实例化方式：
     方式一：通过字面量定义的方式
     方式二：通过new + 构造器的方式

     面试题：String s = new String("abc");方式创建对象，在内存中创建了几个对象？
            答:创建了两个：一个是堆空间中new结构， 另一个是char[] 对应的常量池中的数据："abc"
     */
    @Test
    public void test2(){
        //此时的s1和s2的数据javaEE声明在方法区中的字符串常量池中
        String s1 = "javaEE";
        String s2 = "javaEE";
        //通过new + 构造器的方式：此时s3和s4保存的地址值，是数据所在堆空间中开辟空间以后对应的对应的地址值
        String s3 = new String("javaEE");
        String s4 = new String("javaEE");

        System.out.println(s1 == s2);//true
        System.out.println(s1 == s3);//false
        System.out.println(s1 == s4);//false
        System.out.println(s3 == s4);//false

        System.out.println("*****************");
        Person p1 = new Person("Tom",12);
        Person p2 = new Person("Tom",12);
        System.out.println(p1.name.equals(p2.name));//true
        System.out.println(p1.name == p2.name);//true..因为调用的name是String

        p1.name = "jerry";//p1.name会新造一个String 字符串，不会更改原有的tom
        System.out.println(p2.name);
    }

    /*
    String:字符串，使用一队 ” ” 引起来表示。
    1.String声明为final的，不可被继承
    2.String实现了Serializable接口：表示字符串是支持序列化的
            实现了Comparable接口:表示String可以比较大小
    3.String内部定义了final char[] vale用于存储字符串数据
    4.String:代表不可变的字符序列，简称：不可变性
        体现：1.当对字符串重新赋值时，需要重写指定内存区域赋值。不能使用原有的value进行赋值
             2.当对现有的字符串进行连续操作时，也需要重新指定内存区域赋值，不能使用原有的value进行赋值
             3.当调用String的replace()方法修改指定字符或字符串时。
    5.通过字面量的方式（区别于new）给一个字符串赋值，此时的字符串值声明在字符串常量池中
    6.字符串常量池中是不会存储相同内容的字符串的



     */
    @Test
    public void test1(){
        String s1 = "abc";//字面量的定义方式。
        String s2 = "abc";
        s1 = "hello";//不会改变常量池中的abc的值，是重新 造出来的字符串

        System.out.println(s1 == s2);//比较s1与s2的值 true。当s1重新赋值hello后为false
        System.out.println(s1);//hello
        System.out.println(s2);//abc

        System.out.println("****************");

        String s3 = "abc";
        s3 += "def";
        System.out.println(s3);//abcdef。。。赋值结果是新造。不是改变abc的内容
        System.out.println(s2);//abc  s2的值不变

        System.out.println("***************");

        String s4 = "abc";
        String s5 = s4.replace('a', 'm');
        System.out.println(s4);//abc  改变之后s4的值不变
        System.out.println(s5);//mbc
    }
}
