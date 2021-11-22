package com.atguigu.java;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author: ylkstack  Email:158656887@qq.com
 * @Date: 2021/8/29 22:39
 */
public class Java9Test2 {
    //Java9的新特性10：Stream API的加强
    @Test
    public void test1() {
        List<Integer> list = Arrays.asList(23, 43, 45, 55, 61, 54, 32, 2, 45, 89, 7);
        //takeWhile 返回从开头开始的按照指定规则尽量多的元素。
//        list.stream().takeWhile(x->x<60).forEach(System.out::println);//额结果为：23，43，45，55

        //dropWhile 的行为与takeWhile 相反，返回剩余的元素。(结果包含判定元素)
        list.stream().dropWhile(x -> x < 60).forEach(System.out::println);
    }

    @Test
    public void test2() {
        //此处可以写null
        //of()参数中的多个元素，可以包含null值
        Stream<Integer> stream1 = Stream.of(1, 2, 3,null);
        stream1.forEach(System.out::println);

        //of()参数不能存储单个null值，否则报异常。（如果是两个null可以）
//        Stream<Object> stream2 = Stream.of(null);
//        Stream<Object> stream2 = Stream.of(null,null);
//        stream2.forEach(System.out::println);

        Integer i = 10;
        i = null;
        //ofNullable：形参变量是可以为null值的单个元素
        Stream<Integer> stream3 = Stream.ofNullable(i);
//        stream3.forEach(System.out::println);
        //查看元素个数
        long count = stream3.count();
        System.out.println(count);
    }

    @Test
    public void test3() {
        //jdk8以前
        Stream.iterate(0, x->x+1).limit(10).forEach(System.out::println);
        //java9中新增的iterate重载的方法
        Stream.iterate(0, x->x<100,x->x+1).forEach(System.out::println);
    }

    //java9新特性十一：Optional提供了新的方法stream()
    @Test
    public void test4() {
        List<String> list = new ArrayList<>();
        list.add("Tom");
        list.add("Jerry");
        list.add("Tim");

        Optional<List<String>> optional = Optional.ofNullable(list);
        Stream<List<String>> stream = optional.stream();
//        long count = stream.count();
//        System.out.println(count);//结果为：1（只有一个List元素）
        stream.flatMap(x -> x.stream()).forEach(System.out::println);//结果为list的每个元素

    }
}