package com.atguigu.java2;

import com.atguigu.java1.Person;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * 获取运行时类的方法结构
 * @author: ylkstack  Email:158656887@qq.com
 * @Date: 2021/8/14 22:35
 */
public class MethodTest {
    @Test
    public void test1() {
        Class<Person> clazz = Person.class;
        //getMethods():获取当前运行时类及其素有父类中声明为public权限的方法
        Method[] methods = clazz.getMethods();
        for (Method m :
                methods) {
            System.out.println(m);
        }

        //getDeclaredMethods():获取当前运行时类中声明的所有方法。(不包含父类中声明的)
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method m :
                declaredMethods) {
            System.out.println(m);
        }
    }

    /*
    @Xxxx
    权限修饰符    返回值类型   方法名（参数类型1 形参名1，...）throws XxxException()
     */
    @Test
    public void test2() {
        Class<Person> clazz = Person.class;
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method m :
                declaredMethods) {
            //1.获取方法声明的注解，
            Annotation[] annotations = m.getAnnotations();
            for (Annotation a :
                    annotations) {
                System.out.println(a);
            }
            //2.权限修饰符
            System.out.print(Modifier.toString(m.getModifiers()) + "\t");

            //3.返回值类型
            System.out.print(m.getReturnType().getName() + "\t");

            //4.方法名
            System.out.print(m.getName());//方法名一般后面不需要加换行
            System.out.print("(");
            //5.获取形参列表
            Class<?>[] parameterTypes = m.getParameterTypes();
            //不加 ! 表示没形参，  加了 ! 表示有形参
            if (!(parameterTypes == null && parameterTypes.length == 0)) {
                //因为可能有多个，所以需要循环
                for (int i = 0; i < parameterTypes.length; i++) {
                    //正常下面，如果是一个参数就不需要逗号，如果是多个就需要逗号，处理方法如下
                    if (i == parameterTypes.length - 1) {
                        //判断是不是最后一个，如果是就输出此项，能把后面逗号取消,结束之后需要break结束
                        System.out.print(parameterTypes[i].getName() + "args_" + i);
                        break;
                    }
                    System.out.print(parameterTypes[i].getName() + "args_" + i + ",");
                }
            }
            System.out.print(")");

            //6.抛出的异常
            Class<?>[] exceptionTypes = m.getExceptionTypes();
            if (exceptionTypes.length > 0) {
                System.out.print("throws ");
                for (int i = 0; i < exceptionTypes.length; i++) {
                    if (i == exceptionTypes.length - 1) {
                        System.out.print(exceptionTypes[i].getName());
                        break;
                    }
                    System.out.print(exceptionTypes[i].getName() + ",");
                }
            }
            System.out.println();
        }
    }
}
