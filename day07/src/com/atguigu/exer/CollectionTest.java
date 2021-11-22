package com.atguigu.exer;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * 测试foreach在Collection集合里的方法
 * @author: ylkstack  Email:158656887@qq.com
 * @Date: 2021/7/18 20:45
 */
public class CollectionTest {
    @Test
    public void test(){
        Collection coll = new ArrayList();

        coll.add(123);
        coll.add(456);
        coll.add(343);
        coll.add(343);

        coll.forEach(System.out::println);//Java 8 新特性
    }

    public static List duplicateList(List list) {
        HashSet set = new HashSet();
        set.addAll(list);
        return new ArrayList(set);
    }
    @Test
    public void test2() {
        List list = new ArrayList();
        list.add(new Integer(1));
        list.add(new Integer(2));
        list.add(new Integer(2));
        list.add(new Integer(4));
        list.add(new Integer(4));
        List list2 = duplicateList(list);
        for (Object integer : list2) {
            System.out.println(integer);
        }
    }

    @Test
    public void test3(){
        HashSet set = new HashSet();
        Person p1 = new Person(1001,"AA");
        Person p2 = new Person(1002,"BB");

        set.add(p1);
        set.add(p2);
        System.out.println(set);
        p1.name = "CC";//改完CC后  p1的哈希值可能就不在原来的位置了
        set.remove(p1);//因为上面哈希值改变，remove为空，移除不成功
        System.out.println(set); //结果：1002,"BB"
        set.add(new Person(1001,"CC"));
        System.out.println(set);  //1001,"CC"   1002,"BB"
        set.add(new Person(1001,"AA"));
        System.out.println(set);//1001,"CC"  1002,"BB" 1001,"AA"


    }

}
