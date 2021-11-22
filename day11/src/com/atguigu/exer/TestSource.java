package com.atguigu.exer;


import com.atguigu.java1.Person;
import org.junit.Test;

import java.lang.reflect.Field;

/**
 * 测试反射代码
 * @author: ylkstack  Email:158656887@qq.com
 * @Date: 2021/8/16 23:15
 */
public class TestSource {
    /*
    获取Class实例
     */
    @Test
    public void test1() throws Exception {
        //第一种：调用.class
        Class<Person> clazz = Person.class;
        System.out.println(clazz);
        //第二种：利用对象实现  p.getClass()
        Person p = new Person();
        Class<? extends Person> clazz1 = p.getClass();
        System.out.println(clazz1);
        //第三种：通过Class静态方法 forName() 实现
        Class<?> clazz2 = Class.forName("com.atguigu.java1.Person");
        System.out.println(clazz2);

        System.out.println(clazz == clazz1);
        System.out.println(clazz == clazz2);
    }

    /*
    创建运行时类的对象
     */

    @Test
    public void test2() throws Exception {
        Class<?> clazz2 = Class.forName("com.atguigu.java1.Person");
        //创建运行时类对象
        Object o = clazz2.getDeclaredConstructor().newInstance();
//1.getDeclaredField(String fieldName):获取运行时类中指定变量名的属性
        Field name = clazz2.getDeclaredField("name");
        //2.保证当前属性是可访问的。   此处必须赋值为true
        name.setAccessible(true);
        //3.获取、设置指定对象的此属性值
        //set():参数1：指明设置哪个对象的属性    参数2：将此属性值设置为多少
        name.set(o, "Tom");
        System.out.println(name.get(o));
    }
}
