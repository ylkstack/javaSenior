package com.atguigu.java;

import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

/**
 * @author: ylkstack  Email:158656887@qq.com
 * @Date: 2021/8/29 21:51
 */
public class Java9Test1 {


    //java8中的写法：
    @Test
    public void test1() {
        List<String> namesList = new ArrayList<>();
        namesList.add("Joe");
        namesList.add("Bob");
        namesList.add("Bill");

        //unmodifiableList:只读的意思,返回的namesList是一个只读的集合
        namesList = Collections.unmodifiableList(namesList);
        //此处不可以继续添加了，因为是只读的
//        namesList.add("Tom");
        System.out.println(namesList);
    }

    //java8 创建只读set集合
    @Test
    public void test2() {
        List<String> list = Collections.unmodifiableList(Arrays.asList("a", "b", "c"));
        Set<String> set = Collections.unmodifiableSet(new HashSet<>(Arrays.asList("a", "b", "c")));
        // 如下操作不适用于jdk 8 及之前版本,适用于jdk 9
        Map<String, Integer> map = Collections.unmodifiableMap(new HashMap<>() {
            {
                put("a", 1);
                put("b", 2);
                put("c", 3);
            }
        });
        map.forEach((k, v) -> System.out.println(k + ":" + v));

    }

    @Test
    public void test3() {
        //此时得到的集合list也是一个只读集合
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        //不可再添加数据，否则报异常
//        list.add(6);
    }

    //java9新特性八：集合工厂方法：创建只读集合
    @Test
    public void test4() {
        //创建只读集合list.of()
        List<Integer> list1 = List.of(1, 2, 3, 4, 5);
        //此处不能添加，因为创建的是只读集合
//        list1.add(6);
        System.out.println(list1);

//        创建只读set
        Set<Integer> set1 = Set.of(2300000,1280,54,65,43,76,87,34,46,5,9,40);
        //不能添加
//        set1.add(6);
        System.out.println(set1);

        //创建只读Map
        Map<String, Integer> map = Map.of("Tom", 23, "Jerry", 54);

        //不能添加
//        map.put("Lilei",34);
        System.out.println(map);

        Map<String, Integer> map1 = Map.ofEntries(Map.entry("Tom", 23), Map.entry("Jack", 31));
        //不能添加
        //        map1.put("Lilei", 34);
        System.out.println(map1);
    }

    //java9 新特性9：InputStream的新方法：tranferTo()
    @Test
    public void test5() {
        //获取本地加载器
        ClassLoader cl = this.getClass().getClassLoader();
        try (
                //获取输入流
                InputStream is = cl.getResourceAsStream("hello.txt");
                //输出流
             OutputStream os = new FileOutputStream("src\\hello1.txt")) {
            is.transferTo(os); // 把输入流中的所有数据直接自动地复制到输出流中
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
