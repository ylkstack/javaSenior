package com.atguigu.java;

import org.junit.Test;

import java.util.*;

/**
 * 泛型的使用
 * 1.jdk 5.0新增的特性
 *
 * 2.在集合中使用泛型：
 *   总结：
 *   ①在集合接口或集合类在jdk5.0时都修改为带泛型的结构。
 *   ②在实例化集合类时，可以致命具体的泛型类型
 *   ③指明完以后，在集合类或接口中凡是定义类或接口时，
 *   内部结构(比如：方法、构造器、属性等)使用到类的泛型的位置，都指定未实例化的泛型类型
 *   比如：add(E e)  --->实例化以后：add(Integer e)
 *   ④注意点：泛型的类型必须是类，不能是基本数据类型，需要用到基本数据类型的位置，拿包装类替换
 *   ⑤如果实例化时，没有指明方形的类型，默认类型为java.lang.Object类型。
 *
 * 3.如何自定义泛型结构：泛型类、泛型接口、泛型方法，见：GenericTest1
 * @author: ylkstack  Email:158656887@qq.com
 * @Date: 2021/7/25 8:48
 */
public class GenericTest {//泛型

    //在集合中使用泛型之前的情况
    @Test
    public void test1(){
        ArrayList list = new ArrayList();
        //需求存放学生的成绩
        list.add(78);
        list.add(76);
        list.add(89);
        list.add(88);
        //问题一：类型不安全
        list.add("Tom");

        for (Object score :
                list) {
            //因为上面有String类型，所以只能强转
            //问题二：强转时有可能出现：ClassCastException
            int studentScore = (Integer) score;
            System.out.println(studentScore);
        }

    }

    //在集合中使用泛型的情况:以ArrayList为例：
    @Test
    public void test2(){
        //泛型是类型。只能使用类，不能使用基本数据类型
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(78);
        list.add(87);
        list.add(99);
        list.add(65);
        //编译时，就会进行类型检查，保证数据的安全。防止类型防止错误
//        list.add("Tom");

//        //方式一：
//        for (Integer score :
//                list) {
//            //使用泛型，避免了强转操作。同时也避免了类型转换异常
//            int stuScore = score;
//            System.out.println(stuScore);
//        }
        //方式二：
        Iterator<Integer> iterator = list.iterator();
        while(iterator.hasNext()){
            int stuScore = iterator.next();
            System.out.println(stuScore);
        }
    }

    //在集合中使用泛型的情况：以HashMap为例
    @Test
    public void test3(){
        //因为HashMap声明时声明了两个泛型(key,value)，所以此处要写两个泛型。
        HashMap<String, Integer> map = new HashMap<>();
        map.put("Tom", 87);
        map.put("Jerry", 87);
        map.put("Jack", 87);

        //需要同泛型输入要求一样，不可随意写
//        map.put(123, "ABC");
        //泛型的嵌套。。。因为Entry是内部接口。所以要使用需要Map. 来获取
        //如果需要省略Map. 需要再上面导入improt.java.Util.Map.*
        Set<Map.Entry<String, Integer>> entry = map.entrySet();
        Iterator<Map.Entry<String, Integer>> iterator = entry.iterator();

        while(iterator.hasNext()){
            Map.Entry<String, Integer> e = iterator.next();
            String key = e.getKey();
            Integer value = e.getValue();
            System.out.println(key + "----" + value);
        }
    }
}
