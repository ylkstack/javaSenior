package com.atguigu.java;

import org.junit.Test;

import java.util.*;

/**
 * @author: ylkstack  Email:158656887@qq.com
 * @Date: 2021/7/22 20:54
 */
public class TreeMapTest {//只能按照key排序，不能按照value排序
    //向TreeMap中添加key-value,要求key必须是由同一个类创建的对象
    //因为要按照key进行排序：自然排序、定制排序
    @Test
    public void test1(){
        TreeMap map = new TreeMap();
        User u1 = new User("Tom",23);
        User u2 = new User("Jerry",32);
        User u3 = new User("Jack",20);
        User u4 = new User("Rose",18);

        map.put(u1,98);
        map.put(u2,89);
        map.put(u3,76);
        map.put(u4,100);

        //自然排序，按照姓名从大到小排序
        Set entrySet = map.entrySet();
        Iterator iterator1 = entrySet.iterator();
        while(iterator1.hasNext()){
            Object obj = iterator1.next();
            //entrySet():集合中的元素都是entry。  返回所有key-value对构成的Set集合
            Map.Entry entry = (Map.Entry) obj;//下面输出需要写出来，不然报地址值
            System.out.println(entry.getKey() + "====" + entry.getValue());
        }

    }

    //定制排序(定制排序在构造器里new出来)
    @Test
    public void test2(){
        TreeMap map = new TreeMap(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 instanceof User && o2 instanceof User){
                    User u1 = (User) o1;
                    User u2 = (User) o2;
                    return Integer.compare(u1.getAge(), u2.getAge());
                }
                throw new RuntimeException("输入的类型不匹配！");
            }
        });
        User u1 = new User("Tom",23);
        User u2 = new User("Jerry",32);
        User u3 = new User("Jack",20);
        User u4 = new User("Rose",18);

        map.put(u1,98);
        map.put(u2,89);
        map.put(u3,76);
        map.put(u4,100);

        Set entrySet = map.entrySet();
        Iterator iterator1 = entrySet.iterator();
        while(iterator1.hasNext()){
            Object obj = iterator1.next();
            //entrySet():集合中的元素都是entry。  返回所有key-value对构成的Set集合
            Map.Entry entry = (Map.Entry) obj;//下面输出需要写出来，不然报地址值
            System.out.println(entry.getKey() + "====" + entry.getValue());
        }
    }
}
