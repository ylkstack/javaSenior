package com.atguigu.java;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * 集合元素的遍历操作，使用迭代器Iterator接口
 * 1.内部的方法：hasNext() 和 next() 搭配使用
 * 2.集合对象每次调用iterator()方法都得到一个全新的迭代器对象，
 *   默认游标都在集合 的第一个元素之前。
 * 3.迭代器内部定义了remove(),可以在遍历的时候没删除集合中的元素，
 *   此方法不同于集合直接调用remove()
 * @author: ylkstack  Email:158656887@qq.com
 * @Date: 2021/7/13 22:46
 */
public class IteratorTest {
    @Test
    public void test(){
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new Person("Jerry",20));
        coll.add(new String("Tom"));
        coll.add(false);//Boolean

        Iterator iterator = coll.iterator();

        // next():①指针下移 ②将下移以后集合位置上的元素返回
        //方式一：
//        System.out.println(iterator.next());//结果：123 为第一个元素
//        System.out.println(iterator.next());
//        System.out.println(iterator.next());
//        System.out.println(iterator.next());
//        System.out.println(iterator.next());
//        //超过集合的数量时报异常：NoSuchElementException  没有这个元素异常
//        System.out.println(iterator.next());

        //方式二：
//        for (int i = 0; i < coll.size(); i++) {
//            System.out.println(iterator.next());
//        }

        //方式三：推荐用这个 来遍历集合
        //hasNext():判断是否还有下一个元素。boolean类型的方法
        while(iterator.hasNext()){
            //next():①指针下移 ②将下移以后集合位置上的元素返回
            System.out.println(iterator.next());
        }
    }

    @Test
    public void test2(){
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new Person("Jerry",20));
        coll.add(new String("Tom"));
        coll.add(false);//Boolean

        //错误方式一：
//        Iterator iterator = coll.iterator();
//        while((iterator.next()) != null){
//            System.out.println(iterator.next());
//        }

        //错误方式二：集合对象每次调用iterator()方法都得到一个全新的迭代器对象，
        // 默认游标都在集合 的第一个元素之前。
//        while((coll.iterator().hasNext())){//因为没调用一次 coll.iterator()
//            //就造一个新的对象，指针始终在第一位，死循环。
//            System.out.println(coll.iterator().next());
//        }


    }

    //测试Iterator中的remove()
    //如果还为调用next()或在上一次调用next方法之后已经调用了remove 方法。
    // 再调用remove都会报 IllegaLStateException 异常
    @Test
    public void test3(){
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new Person("Jerry",20));
        coll.add(new String("Tom"));
        coll.add(false);//Boolean

        Iterator iterator = coll.iterator();
        while(iterator.hasNext()){
            Object obj = iterator.next();
            if ("Tom".equals(obj)){
                iterator.remove();
            }
        }

        //遍历集合
        //上面指针已经到下部，需要重新生成对象
        iterator = coll.iterator();//重新生成的对象
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
