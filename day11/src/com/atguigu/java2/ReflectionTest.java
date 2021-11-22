package com.atguigu.java2;

import com.atguigu.java1.Person;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 调用运行时类中指定的结构：属性、方法、构造器
 * @author: ylkstack  Email:158656887@qq.com
 * @Date: 2021/8/15 21:16
 */
public class ReflectionTest {
    @Test
    public void testField() throws Exception {
        Class<Person> clazz = Person.class;

        //创建运行时类的对象
        Person person = clazz.getDeclaredConstructor().newInstance();
        //获取指定的属性:要求运行时类中属性声明为public
        //通常不采用此方法
        Field id = clazz.getField("id");

        /*
        设置当前属性的值
        set():参数1：指明设置哪个对象的属性    参数2：将此属性值设置为多少
         */
        id.set(person, 1001);

        /*
        获取当前属性的值
        get():参数1：获取哪个对象的当前属性值
         */
        //默认返回是Object类型的，实际上我们造的是int类型的
        int pId = (int) id.get(person);
        System.out.println(pId);
    }

    /*
    如何去操作运行时类中的指定的属性-----需要掌握
     */
    @Test
    public void testField1() throws Exception {
        Class<Person> clazz = Person.class;

        //创建运行时类的对象
        Person person = clazz.getDeclaredConstructor().newInstance();

        //1.getDeclaredField(String fieldName):获取运行时类中指定变量名的属性
        Field name = clazz.getDeclaredField("name");

        //2.保证当前属性是可访问的。   此处必须赋值为true
        name.setAccessible(true);
        //3.获取、设置指定对象的此属性值
        //set():参数1：指明设置哪个对象的属性    参数2：将此属性值设置为多少
        name.set(person, "Tom");

        System.out.println(name.get(person));
    }

    /*
    如何操作运行时类的方法
     */
    @Test
    public void testField2() throws Exception {
        Class<Person> clazz = Person.class;

        //创建运行时类对象
        Person person = clazz.getDeclaredConstructor().newInstance();

        /*
        1.获取指定的某个方法
        getDecloredMethod():参数1：指明获取的方法的名称    参数2：指明获取方法的形参列表
         */
        Method show = clazz.getDeclaredMethod("show", String.class);
        //2.保证当前方法是可访问的
        show.setAccessible(true);
        /*
        3.调用方法的invoke():参数1：方法的调用者，   参数2：给方法形参赋值的实参
        invoke()的方绘制即为对应类中调用的方法的返回值
         */
        Object returnVale = show.invoke(person, "CHN");
        System.out.println(returnVale);

        System.out.println("*********如何调用静态方法**************");

        //才做运行时类的静态方法的方法
        //private static void showDesc()

        Method showDesc = clazz.getDeclaredMethod("showDesc");
        showDesc.setAccessible(true);
        //如果调用的运行时类中的方法没有返回值，则invoke()返回null
        //调用方法的invoke():参数1：方法的调用者（即当前类、即clazz\Person.class），   参数2：给方法形参赋值的实参
//        Object returnValue = showDesc.invoke(null);//此处形参也可以写null...
        Object returnValue = showDesc.invoke(Person.class);
        System.out.println(returnValue);//null...因为上面无返回值
    }

    /*
    如何调用运行时类中的指定的构造器
     */

    @Test
    public void testConstructor() throws Exception {
        Class<Person> clazz = Person.class;

        //private Person(String name)
        /*
        1.获取指定的构造器
        getDeclaredConstructor():参数：指明构造器的参数列表
         */
        Constructor<Person> constructor = clazz.getDeclaredConstructor(String.class);

        //2.保证此构造器是可访问的------形参设为：true
        constructor.setAccessible(true);

        //3.调用此构造器创建运行时类的对象
        Person per = constructor.newInstance("Tom");
        System.out.println(per);
    }
}
