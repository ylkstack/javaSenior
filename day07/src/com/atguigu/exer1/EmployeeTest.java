package com.atguigu.exer1;

import org.junit.Test;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * 创建该类的 5 个对象，并把这些对象放入 TreeSet 集合中（下一章：
 * TreeSet 需使用泛型来定义）
 * 分别按以下两种方式对集合中的元素进行排序，并遍历输出：
 * 1). 使 Employee 实现 Comparable 接口，并按 name 排序
 * 2). 创建 TreeSet 时传入 Comparator 对象，按生日日期的先后排序。
 * @author: ylkstack  Email:158656887@qq.com
 * @Date: 2021/7/18 21:47
 */
public class EmployeeTest {
    //问题二：定制排序、按照生日的先后排序
    @Test
    public void test2() {
        //创建TreeSet集合对象
        TreeSet set = new TreeSet(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 instanceof Employee && o2 instanceof Employee) {
                    Employee e1 = (Employee) o1;
                    Employee e2 = (Employee) o2;

                    //创建生日对象
                    MyDate b1 = e1.getBirthday();
                    MyDate b2 = e2.getBirthday();

                    //方式一：
//                    //比较年
//                    int minusYear = b1.getYear() - b2.getYear();
//                    if (minusYear != 0){
//                        return minusYear;
//                    }
//                    //比较月
//                    int minusMonth = b1.getMonth() - b2.getMonth();
//                    if (minusMonth != 0){
//                        return minusMonth;
//                    }
//                    //比较日
//                    return b1.getDay() - b2.getDay();

                    //方式二：
                    return b1.compareTo(b2);
                }
//                return 0;
                throw new RuntimeException("传入的数据类型不一致");
            }
        });
        //创建5个对象
        Employee e1 = new Employee("liudehua", 55, new MyDate(1965, 5, 4));
        Employee e2 = new Employee("zhangxueyou", 43, new MyDate(1987, 5, 4));
        //如果 日期数据相同，则下面的对象如法添加 TreeSet
        Employee e3 = new Employee("guofucheng", 44, new MyDate(1987, 5, 4));
        Employee e4 = new Employee("liming", 51, new MyDate(1954, 8, 12));
        Employee e5 = new Employee("liangchaowei", 21, new MyDate(1978, 12, 4));
        //添加数据
        set.add(e1);
        set.add(e2);
        set.add(e3);
        set.add(e4);
        set.add(e5);

        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    //问题一：使用自然排序
    @Test
    public void test1(){
        //创建TreeSet集合对象
        TreeSet set = new TreeSet();
        //创建5个对象
        Employee e1 = new Employee("liudehua",55,new MyDate(1965, 5, 4));
        Employee e2 = new Employee("zhangxueyou",43,new MyDate(1987, 5, 4));
        Employee e3 = new Employee("guofucheng",44,new MyDate(1987, 5, 9));
        Employee e4 = new Employee("liming",51,new MyDate(1954, 8, 12));
        Employee e5 = new Employee("liangchaowei",21,new MyDate(1978, 12, 4));
        //添加数据
        set.add(e1);
        set.add(e2);
        set.add(e3);
        set.add(e4);
        set.add(e5);

        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
