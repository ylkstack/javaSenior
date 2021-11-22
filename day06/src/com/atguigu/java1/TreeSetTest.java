package com.atguigu.java1;

import org.junit.Test;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * @author: ylkstack  Email:158656887@qq.com
 * @Date: 2021/7/17 22:28
 */
public class TreeSetTest {
    /*
    1.向TreeSet中添加的数据，要求是相同类的对象，
    2.两种排序方式：自然排序(实现Comparable接口) 和 定制排序（Comparator）

    3.自然排序中，比较两个对象是否相同的标准为： comepareTo() 返回0.不在是equals()
    4.定制排序中，比较两个对象是否相同的标准为：compare()返回0，不在是equals().
     */
    @Test
    public void test1(){
        TreeSet set = new TreeSet();

        //失败：不能添加不同类的对象
//        set.add(123);
//        set.add(456);
//        set.add("AA");
//        set.add(new User("Tom", 12));

        //举例一：  相同类的对象可以添加，  Integer类默认为从小到大排序
//        set.add(34);
//        set.add(-34);
//        set.add(43);
//        set.add(11);
//        set.add(8);

        //举例二：
        set.add(new User("Tom", 12));
        set.add(new User("Jerry", 32));
        set.add(new User("Jim", 2));
        set.add(new User("Mike", 65));
        set.add(new User("Jack", 33));
        set.add(new User("Jack", 56));


        //遍历数据，使用迭代器--使用hasNext和next配合使用
        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    @Test
    public void test2(){
        Comparator com = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 instanceof User && o2 instanceof User){
                    User u1 = (User) o1;
                    User u2 = (User) o2;
                    return Integer.compare(u1.getAge(), u2.getAge());
                }else {
                    throw new RuntimeException("输入的数据类型不匹配");
                }
            }
        };

        TreeSet set = new TreeSet(com);// com为上面定制的Comparator排序方法，
        // 形参里写com,就按照定制的方法排序，如果无，则按照自然排序
        set.add(new User("Tom", 12));
        set.add(new User("Jerry", 32));
        set.add(new User("Jim", 2));
        set.add(new User("Mike", 65));
        set.add(new User("Jack", 33));
        set.add(new User("Marry", 33));
        set.add(new User("Jack", 56));

        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
