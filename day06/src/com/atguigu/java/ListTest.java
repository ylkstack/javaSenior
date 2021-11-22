package com.atguigu.java;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * 1.list接口框架
 *  丨----Collection接口：单列集合，用来存储一个个的对象，
 *     丨----List接口：存储的有序的，可重复的数据。   --->“动态”数组，替换原有的数组
 *        丨----ArrayList：作为List接口的主要实现类，线程不安全的，效率高；底层使用Object[] elementData 存储
 *        丨----LinkedList：对于频繁的插入，删除操作。使用此类效率比ArrayList高；底层使用双向链表
 *        丨----Vector：作为List接口的古老实现类，线程安全的，效率低；底层使用Object[] elementData 存储存储
 *
 *  2. Arraylist的原码分析：
 *  2.1 jdk 1.7 情况
 *      ArrayList list = new ArrayList；//创建了长度是10的Object
 *      list.add(123);//elementData[0] = new Integer(123);
 *      ...
 *      list.add(11);//如果此次的添加导致底层elementData数组容量不够，则扩容。
 *      默认情况下，扩容为原来的容量的1.5倍，同时需要将原有数组中的数据复制到新的数组中。
 *
 *      结论：建议开发中使用带参构造器：ArrayList list = new ArrayList(int capacity)
 *
 *  2.2 jdk 8中Arraylist的变化
 *      ArrayList list = new ArrayList();//底层Object[] elementData初始化为{},并没有创建长度为10的数组
 *
 *      list.add(123);//第一次调用add()时，底层才创建了长度为10的数组，并将数据123添加到 elementData[0]
 *      ...
 *      后续的添加和扩容操作与jdk 7 无异。
 *  2.3 小结：jdk 7中的ArrayList的创建类似于单例的饿汉式，而jdk8中的ArrayList的对象
 *           的创建类似于单例的懒汉式，延迟的数组的创建，节省内存
 *
 *  3. LinkedList的原码分析：
 *      LinkedList list = new LinkedList();//内部声明了Node类型的first和last属性，默认值为：null
 *      list.add(123);//将123封装到Node中，创建了Node对象。
 *      其中，Node定义为： 体现了LinkedList的双向链表的说法
 *      private static class Node<E> {
 *        E item;
 *        Node<E> next;
 *        Node<E> prev;

 *        Node(Node<E> prev, E element, Node<E> next) {
 *        this.item = element;
 *        this.next = next;
 *        this.prev = prev;
 *        }
 *        }
 *
 *  4. Vector 的原码分析：jdk7和jdk8中通过Vector()构造器创建对象时，底层都创建了长度为10的数组
 *      在扩容方面，默认扩容为原来的数组长度的2倍
 *
 *  面试题：ArrayList、LinkedList、Vector三者的异同？
 *  同：三个类都实现了List接口，存储数据的特点相同：存储有序的、可重复的数据
 *  不同：见上
 *
 *
 *
 * @author: ylkstack  Email:158656887@qq.com
 * @Date: 2021/7/14 18:22
 */
public class ListTest {

    /*
void add(int index, Object ele):在index位置插入ele元素
boolean addAll(int index, Collection eles):从index位置开始将eles中
的所有元素添加进来
Object get(int index):获取指定index位置的元素
int indexOf(Object obj):返回obj在集合中首次出现的位置
int lastIndexOf(Object obj):返回obj在当前集合中末次出现的位置
Object remove(int index):移除指定index位置的元素，并返回此元素
Object set(int index, Object ele):设置指定index位置的元素为ele
List subList(int fromIndex, int toIndex):返回从fromIndex到toIndex
位置的子集合

总结：常用方法，
增：add(Object obj)
删:remove(int index)/remove(Object obj):移除指定index位置的元素，并返回此元素
改：set(int index, Object ele):设置指定index位置的元素为ele
查：get(int index):获取指定index位置的元素
插：add(int index, Object ele):在index位置插入ele元素
长度：size()
遍历：①Iterator迭代器方式
     ②增强for循环
     ③普通的循环
     */
    @Test
    public void test3(){
        ArrayList list = new ArrayList();
        list.add(123);
        list.add(456);
        list.add("AA");
        //方式一：Iterator迭代器方式
        Iterator iterator = list.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
        System.out.println("***************");
        //方式二：foreach循环，增强for循环
        for (Object obj :
                list) {
            System.out.println(obj);
        }

        //方式三：普通for循环
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
    @Test
    public void test2(){
        ArrayList list = new ArrayList();
        list.add(123);
        list.add(456);
        list.add("AA");
        list.add(new Person("Tom", 12));
        list.add(456);

        //int indexOf(Object obj):返回obj在集合中首次出现的位置，如果不存在返回 -1
        int index = list.indexOf(456);
        System.out.println(index);//结果为：1,如果索引到返回索引位置，索引不到返回-1

        //int lastIndexOf(Object obj):返回obj在当前集合中末次出现的位置,如果不存在返回 -1
        System.out.println(list.lastIndexOf(456));

        //Object remove(int index):移除指定index位置的元素，并返回此元素
        Object obj = list.remove(0);
        System.out.println(obj);//结果为：123  obj返回remove要移除的元素
        System.out.println(list);//结果为：[456, AA, Person{name='Tom', age='12'}, 456]

        //Object set(int index, Object ele):设置指定index位置的元素为ele
        list.set(1, "CC");
        System.out.println(list);//结果为：把AA改为了CC

        //List subList(int fromIndex, int toIndex):返回从fromIndex到toIndex 的左闭右开的子集合
        List subList = list.subList(2, 4);
        System.out.println(subList);//返回值为左闭右开。
        System.out.println(list);//返回值未改变，subList结果不会改变原有List
    }
    @Test
    public void test1(){
        ArrayList list = new ArrayList();
        list.add(123);
        list.add(456);
        list.add("AA");
        list.add(new Person("Tom", 12));
        list.add(456);

        System.out.println(list);
        //void add(int index, Object ele):在index位置插入ele元素
        list.add(1, "BB");
        System.out.println(list);

        //boolean addAll(int index, Collection eles):从index位置开始将eles中
        List list1 = Arrays.asList(1, 2, 3);
        list.addAll(list1);
//        list.add(list1);//如果使用add添加，则添加的是一个数组。算一个元素的所有元素添加进来
        System.out.println(list);//结果为：9

        //Object get(int index):获取指定index位置的元素
        System.out.println(list.get(1));//结果：BB
    }
    @Test
    public void test8(){
        ArrayList list = new ArrayList();
        list.add(123);
        list.add(456);
        list.add(new Person("Tom",22));
        list.add(789);
//迭代器遍历
        Iterator iterator = list.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
//foreach方法
//        for(Object obj : list){
//            System.out.println(obj);
//        }
//普通for循环
//        for(int i = 0;i < list.size();i++){
//            System.out.println(list.get(i));
//        }
    }
//重复测试
    @Test
    public void test9(){
        ArrayList list = new ArrayList();
        list.add(123);
        list.add(123);
        list.add(new Person("tom",22));
        list.add(123);
//迭代器遍历
        Iterator iterator = list.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
//foreach方法
        for(Object obj : list){
            System.out.println(obj);
        }
//普通for循环
        for(int i = 0;i < list.size();i++){
            System.out.println(list.get(i));
        }
    }

}
