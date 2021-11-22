package com.atguigu.java2;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * 一、集合框架的概念
 *
 * 1.集合、数组都是对多个数据进行存储操作的结构，简称Java容器
 *  说明：此时的存储，主要指的是内存层面的存储，不涉及到持久化的存储（.txt,.jpg,.avi,数据库）
 *
 * 2.1数据在存储多个数据方面的特点：
 *      >一旦初始化以后，其长度就确定了。
 *      >数组一旦定义好，其元素的类型也就确定了，我们也就只能操作指定类型的数据了
 *         比如：String[] arr;int[] arr1;Object[] arr2;
 * 2.2 数组在存储多个数据方面的缺点
 *      > 一旦初始化以后，其长度就不可修改
 *      > 数组中提供的方法非常有限，对于添加、删除、插入数据等操作，非常不便，同时效率也不高
 *      > 获取数组中实际圆度的个数的需求，数组没有现成的属性或方法可用。
 *      > 数组存储数据的特点: 有序、可重复、对于无序、不可重复的需求，不能满足。
 *
 * 二、集合框架
 *      丨----Collection接口：单列集合，用来存储一个个的对象，
 *          丨----List接口：存储的有序的，可重复的数据。   --->“动态”数组
 *              丨----ArrayList、LinkedList、Vector
 *          丨----Set接口：存储无序的，不可重复的数据。    --->类似于高中的集合
 *              丨----HashSet、LinkedHashSet、TreeSet
 *     丨----Map接口:双列集合，用来存储一对key - value（）一对的数据    --->高中的函数: y = f(X)
 *              丨----HashMap、LinkedHashMap、TreeMap、Hashtable、Properties
 *
 * 三、Collection接口中的方法的使用
 *
 * @author: ylkstack  Email:158656887@qq.com
 * @Date: 2021/7/9 23:56
 */
public class CollectionTest {

    @Test
    public void test1(){
        Collection coll = new ArrayList();
        //add(Object e):将元素e添加到集合coll中
        coll.add("AA");
        coll.add("BB");
        coll.add(123);//自动装箱
        coll.add(new Date());

        //size():获取添加的元素的个数
        System.out.println(coll.size());//结果为4。。。。如果上面未添加，结果为0

        //addAll(Collection cloo1):将coll1集合中的元素添加到当前集合中
        Collection coll1 = new ArrayList();
        coll1.add(456);
        coll1.add("CC");
        coll.addAll(coll1);
        System.out.println(coll.size());//结果为：6
        System.out.println(coll);//此处的toString 显示的是上部 new ArrayList()的结果

        //clear():清空集合元素
        coll.clear();//清除的是元素。只是清空里面的内容，不是重新赋值为Null,是为0

        //isEmpty():判断当前集合是否为空
        System.out.println(coll.isEmpty());//false...上面调用clear()后为true


    }
}
