package com.atguigu.java2;

import com.atguigu.java1.Person;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * 获取当前运行时类的属性结构
 * @author: ylkstack  Email:158656887@qq.com
 * @Date: 2021/8/14 21:56
 */
public class FieldTest {
    @Test
    public void test1(){
        Class<Person> clazz = Person.class;

        //获取属性结构
        //getFields():获取当前运行时类及其弗雷中声明为public访问权限的属性
        Field[] fields = clazz.getFields();
        for (Field f :
                fields) {
            System.out.println(f);
        }

        //getDeclaredFields():获取当前运行时类中声明的所有属性。（不包含父类中声明的属性）
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field f :
                declaredFields) {
            System.out.println(f);
        }
    }
    //权限修饰符 数据类型 变量名
    @Test
    public void test2(){
        Class<Person> clazz = Person.class;
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field f :
                declaredFields) {
            //1.权限修饰符
            int modifiers = f.getModifiers();
            //直接调用的时候权限修饰符默认显示的是数字：0为缺省 1为Public 2为private
            //想直接显示出来需要调用Modifier类的toString()
            System.out.print(Modifier.toString(modifiers) + "\t");//结果为：private  public
            //2.数据类型
            Class<?> type = f.getType();
            System.out.print(type.getName() + "\t");
            //3.变量名
            String fName = f.getName();
            System.out.println(fName);
            System.out.println();

            /*
            输出结果为：第一次是权限修饰符-->加数据类型--->加变量名
            private	java.lang.String	name

                    int             	age

            public	int	                id
             */
        }
    }
}
