package com.atguigu.java;

import org.junit.Test;

/**
 * 关羽StringBuffer和StringBuilder的使用
 *
 *
 * @author: ylkstack  Email:158656887@qq.com
 * @Date: 2021/6/22 22:52
 */
public class StringBufferBuilderTest {

    /*
StringBuffer append(xxx)：提供了很多的append()方法，用于进行字符串拼接//添加的意思
StringBuffer delete(int start,int end)：删除指定位置的内容
StringBuffer replace(int start, int end, String str)：把[start,end)位置替换为str
StringBuffer insert(int offset, xxx)：在指定位置插入xxx
StringBuffer reverse() ：把当前字符序列逆转
public int indexOf(String str)：返回str字符串在 当前字符串中首次出现的位置
public String substring(int start,int end)：返回一个从start开始到end索引结束的左闭右开区间的子字符串
public int length()：
public char charAt(int n )：
public void setCharAt(int n ,char ch)：

当append和insert时，如果原来value数组长度不够，可扩容。
如上这些方法支持方法链操作。

        总结：
        增：append(xxx)：提供了很多的append()方法，用于进行字符串拼接//添加的意思
        删：delete(int start,int end)：删除指定位置的内容
        改：replace(int start, int end, String str)：把[start,end)位置替换为str
        查：charAt(int n )：查询
        插：insert(int offset, xxx)：在指定位置插入xxx
        长度：length()：
        遍历：for() + charat()

     */
/*
对比String、StringBuffer、StringBuilder三者的效率
从高到底排列：StringBuilder > StringBuffer > String
 */
    @Test
    public void test3(){
        //初始设置
        long startTime = 0L;
        long endTime = 0L;
        String text = "";
        StringBuffer buffer = new StringBuffer("");
        StringBuilder builder = new StringBuilder("");
//开始对比
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 20000; i++) {
            buffer.append(String.valueOf(i));
        }
        endTime = System.currentTimeMillis();
        System.out.println("StringBuffer的执行时间：" + (endTime - startTime));

        startTime = System.currentTimeMillis();
        for (int i = 0; i < 20000; i++) {
            builder.append(String.valueOf(i));
        }
        endTime = System.currentTimeMillis();
        System.out.println("StringBuilder的执行时间：" + (endTime - startTime));

        startTime = System.currentTimeMillis();
        for (int i = 0; i < 20000; i++) {
            text = text + i;
        }
        endTime = System.currentTimeMillis();  System.out.println("String的执行时间：" + (endTime - startTime));

    }

    @Test
    public void test2(){
        StringBuffer s1 = new StringBuffer("abc");
//        append()---添加的意思。
        s1.append(1);
        s1.append('1');
        System.out.println(s1);
        // delete(int start,int end)：删除指定位置的内容//左闭 右开
//        s1.delete(2, 4);
        //replace(int start, int end, String str)：把[start,end)位置替换为str
//        s1.replace(2, 4, "hello");
        //insert(int offset, xxx)：在指定位置插入xxx
//        s1.insert(2, false);
        // StringBuffer reverse() ：把当前字符序列逆转//也就是反转
        s1.reverse();
        //substring(int start,int end)：返回一个从start开始到end索引结束的左闭右开区间的子字符串
        //此方法需要返回值，不会更改原字符串信息，更改的是返回的子字符串
        String s2 = s1.substring(1, 3);
        System.out.println(s1);
        System.out.println(s1.length());
        System.out.println(s2);

    }



    /*
    String、StringBuffer、StringBuilder三者的异同？
    String:不可变的字符序列：底层使用char[]存储
    StringBuffer:可变的字符序列：线程安全的，效率低：底层使用char[]存储
    StringBuilder:可变的字符序列：jdk5.0新增的，线程不安全的，效率高：底层使用char[]存储
    相同的：都是和字符串相关的，底层都使用char[]存储
    buffer数组默认是 16 个字符

    原码分析：                       底层做的事情
    String str = new String();//new char[0];
    String str1 = new String("abc");//new char[]{'a','b','c'};

    StringBuffer sb1 = new StringBuffer();//new char[16];底层创建了一个长度是16的数组
    System.out.println(sb1.length());//16
    sb1.append('a');//value[0] == 'a';    append添加
    sb1.append('b');//value[1] == 'b';

StingBuffer sb2 = new StringBuffer("abc");//char[] value = new char["abc".length() + 16];

//问题1：System.out.println(sb2.length());//结果为：3  有几个值 长度为几
//问题2：扩容问题：如果要添加的数据底层数组盛不下了，那就要扩容底层的数组。
        默认情况下，扩容为原来容量的2倍，同时将原有数组中的元素赋值到新的数组中

        指导意义：开发中建议大家使用：StringBuffer(int capacity)或StringBuidler(int capacity)
     */

    @Test
    public void test1(){
        StringBuffer sb1 = new StringBuffer("abc");
        sb1.setCharAt(0, 'm');//无返回值，直接变更字符串内容。
        System.out.println(sb1);

        StringBuffer sb2 = new StringBuffer();
        System.out.println(sb2.length());//结果为：0
    }
}
