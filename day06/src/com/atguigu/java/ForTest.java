package com.atguigu.java;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

/**
 * jdk 5.0 新增了 foreach 循环(增强for循环)，用于遍历集合、数组
 *
 *
 * @author: ylkstack  Email:158656887@qq.com
 * @Date: 2021/7/13 23:34
 */
public class ForTest {
    @Test
    public void test(){
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new Person("Jerry",20));
        coll.add(new String("Tom"));
        coll.add(false);//Boolean

        //for(集合元素的类型 局部变量 ： 集合对象)
        //内部仍然调用了迭代器。
        for (Object obj :
             coll) {
            System.out.println(obj);
        }
    }

    @Test
    public void test2(){
        int[] arr = new int[]{1,2,3,4,5,6};
        for (int i :
                arr) {
            System.out.println(i);
        }
    }

    @Test
    public void test3(){
        String[] arr = new String[]{"MM","MM","MM"};

        //方式一：普通for赋值
//        for (int i = 0; i < arr.length; i++) {
//            arr[i] = "GG";
//        }

        //方式二：增强for循环---->里面值变更不会改变外面变量的值。因为arr的值赋给 s 。
        for (String s :
                arr) {
            s = "GG";
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
