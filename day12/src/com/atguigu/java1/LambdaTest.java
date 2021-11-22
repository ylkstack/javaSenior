package com.atguigu.java1;

import org.junit.Test;

import java.util.Comparator;

/**
 * Lambda表达式的使用举例
 *
 * @author: ylkstack  Email:158656887@qq.com
 * @Date: 2021/8/19 22:54
 */
public class LambdaTest {

    @Test
    public void test1() {
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("我爱北京天安门");
            }
        };
        r1.run();

        System.out.println("**********************");

        //Lambda表达式法实现
        Runnable r2 = () ->System.out.println("我爱北京故宫");

        r2.run();
    }

    @Test
    public void test2() {
        Comparator<Integer> comparator1 = new Comparator<>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };
        int compare1 = comparator1.compare(12, 21);
        System.out.println(compare1);

        System.out.println("**************************");
        //使用Lambda表达式的写法
        Comparator<Integer> comparator2 = ((o1, o2) -> Integer.compare(o1, o2));
        int compare2 = comparator2.compare(32, 21);
        System.out.println(compare2);
        //
        System.out.println("**************************");
        //方法引用的写法
        Comparator<Integer> comparator3 = Integer :: compare;
        int compare3 = comparator3.compare(32, 21);
        System.out.println(compare3);

    }
}
