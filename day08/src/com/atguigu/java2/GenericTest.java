package com.atguigu.java2;

import com.atguigu.java1.Student;
import org.junit.Test;

import java.util.*;

/**
 * 1.泛型在继承方面的体现
 *
 *
 * 2.通配符的使用
 *
 *
 * @author: ylkstack  Email:158656887@qq.com
 * @Date: 2021/7/25 21:08
 */
public class GenericTest {

    /*
    1.泛型的继承方面的体现

      虽然类A是类B的父类，但是G<A> 和G<B> 二者不具备子父类关系，二者是并列关系。

         补充：类A是类B的父类：A<G> 和 B<G> 的父类
     */
    @Test
    public void test1(){
        Object obj = null;
        String str = null;

        obj = str;//类的层面可以直接赋值；

        Object[] arr1 = null;
        String[] arr2 = null;
        arr1 = arr2;
        //编译不通过
//        Date date = new Date();
//        str = date;//不允许，无子父类关系

        List<Object> list1 = null;
        List<String> list2 = new ArrayList<String>();
        //此时的list1和list2的类型不具有子父类关系，
        //编译不通关
//        list1 = list2;//此种不可以直接两个赋值，
        /*
        反证法：
        假设 list1 = list2;
        list1.add(123);导致混入非String的数据,出错

         */
        show(list1);
        show1(list2);//此处不能写show() 因为不同类型

    }
    public void show1(List<String> list){

    }
    public void show(List<Object> list){

    }


    @Test
    public void test2(){
        AbstractList<String> list1 = null;
        List<String> list2 = null;
        ArrayList<String> list3 = null;
        list1 = list3;//此种可以，因为泛型一直
        list2 = list3;

        List<String> list4 = new ArrayList<>();
    }

    /*
    2.通配符的使用
      通配符：？

      类A是类B的父类,G<A> 和G<B>是没有关系的，二者共同的父类是：G<?>
     */
    @Test
    public void test3(){
        List<Object> list1 = null;
        List<String> list2 = null;

        List<?> list = null;

        list = list1;
        list = list2;

        //编译通过
//        print(list1);
//        print(list2);

        //
        List<String> list3 = new ArrayList<>();
        list3.add("AA");
        list3.add("BB");
        list3.add("CC");
        list = list3;
        //添加(获取):对于list<?> 就不能向其内部添加数据，
        //除了添加null之外。
//        list.add("DD");
//        list.add('?');
        list.add(null);

        //获取(读取)：允许读取数据，读取的数据类型为Object。
        Object o = list.get(0);
        System.out.println(o);
    }

    public void print(List<?> list){
        Iterator<?> iterator = list.iterator();
        while (iterator.hasNext()){
            Object obj = iterator.next();
            System.out.println(obj);
        }
    }

    /*
    3.有限制条件的通配符的使用
    ? extends A:
             G<? extends A> 可以作为G<A> 和G<B>的父类，其中B是A的子类

    ? super A:
          G<? super A> 可以作为G<A> 和G<B>的父类，其中B是A的父类
     */

    @Test
    public void test4(){

        List<? extends Person1> list1 = null;
        List<? super Person1> list2 = null;

        List<Student> list3 = new ArrayList<Student>();
        List<Person1> list4 = new ArrayList<Person1>();
        List<Object> list5 = new ArrayList<Object>();

//        list1 = list3;
        list1 = list4;
//        list1 = list5;

//        list2 = list3;
        list2 = list4;
        list2 = list5;

        //读取数据:
        list1 = list4;
        Person1 p = list1.get(0);
        //编译不通过
//        Student s = list1.get(0);
        list2 = list4;
        Object obj = list2.get(0);
        System.out.println(p);

        //写入数据：因为有限条件泛型子类，是负无穷小，会出现父类赋值子类情况
        //所以编译不通过
//        list1.add(new Student());

        // ? super A   正无穷大，
        //编译通过
//        list2.add(new Person());
//        list2.add(new Student());
    }
}
