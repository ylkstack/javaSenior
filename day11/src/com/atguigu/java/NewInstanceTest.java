package com.atguigu.java;

import org.junit.Test;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

/**
 * @author: ylkstack  Email:158656887@qq.com
 * @Date: 2021/8/14 20:07
 */
public class NewInstanceTest {
    @Test
    public void test1() throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Class<Person> calzz = Person.class;
        /*
        newInstance():调用此方法，创建对应的运行时类的对象，内部调用了运行时类的空参构造器

        要想此方法正常的创建运行时类的对象，要求：
        1.运行时类必须提供空参的构造器
        2.空参的构造器的访问权限得够，通常设置为public

        在javabean中要求提供一个public的空参构造器，原因：
        1.便于通过反射，创建运行时类的对象
        2。便于子类继承此运行时类时，默认调用super()时，保证父类有此构造器
         */
//        Person obj = calzz.newInstance();//此方法已过时
        Person obj = calzz.getDeclaredConstructor().newInstance();
        System.out.println(obj);
    }

    //以下代码体会反射的动态性，创建对象只有在运行时才知道创建的是哪个对象
    @Test
    public void test2() {
        for (int i = 0; i < 100; i++) {

            int num = new Random().nextInt(3);
            //声明一个String供下面用
            String classPath = "";
            switch (num) {
                case 0:
                    classPath = "java.util.Date";
                    break;
                case 1:
                    classPath = "java.lang.Object";
                    break;
                case 2:
                    classPath = "com.atguigu.java.Person";
                    break;
            }
            try {
                Object obj = getInstance(classPath);
                System.out.println(obj);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
    /*
    创建一个指定类的对象
    classPath:指定类的全类名
     */
    public Object getInstance(String classPath) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class<?> clazz = Class.forName(classPath);

        return clazz.newInstance();
    }
}
