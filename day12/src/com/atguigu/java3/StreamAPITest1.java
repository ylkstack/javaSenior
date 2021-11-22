package com.atguigu.java3;

import com.atguigu.java2.Employee;
import com.atguigu.java2.EmployeeData;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 测试Stream的中间操作
 *
 * @author: ylkstack  Email:158656887@qq.com
 * @Date: 2021/8/22 21:54
 */
public class StreamAPITest1 {
    //1.筛选与切片
    @Test
    public void test1() {
        List<Employee> list = EmployeeData.getEmployees();
//        filter(Predicate p)	接收 Lambda ， 从流中排除某些元素
        Stream<Employee> stream = list.stream();
        //练习：查询员工表中薪资大于7000的员工信息
        stream.filter(employee -> employee.getSalary() > 7000).forEach(System.out::println);
        System.out.println();

//        limit(long maxSize)	截断流，使其元素不超过给定数量
        //因为上面执行了终止操作，所以下面调用需要重新赋值声明stream,  list.stream()
        list.stream().limit(3).forEach(System.out::println);
        System.out.println();
//        skip(long n)	跳过元素，返回一个扔掉了前 n 个元素的流。若流中元素不足 n 个，则返回一 个空流。与 limit(n) 互补
        list.stream().skip(3).forEach(System.out::println);
        System.out.println();
//        distinct()  筛选，通过流所生成元素的 hashCode() 和 equals() 去除重复元素
        list.add(new Employee(1010, "刘强东", 40, 8000));
        list.add(new Employee(1010, "刘强东", 40, 8000));
        list.add(new Employee(1010, "刘强东", 40, 8000));
        list.add(new Employee(1010, "刘强东", 40, 8000));
        list.add(new Employee(1010, "刘强东", 40, 8000));
//        System.out.println(list);   //测试是否添加成功
        list.stream().distinct().forEach(System.out::println);
    }

    //映射
    @Test
    public void test2() {

//        map(Function f)	接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
        List<String> list = Arrays.asList("aa", "bb", "cc", "dd");
        list.stream().map(str->str.toUpperCase()).forEach(System.out::println);
        System.out.println();
        //练习1：获取员工姓名长度大于3的员工的姓名
        List<Employee> employees = EmployeeData.getEmployees();
        //map()里使用的第三种引用方式
        Stream<String> namesStream = employees.stream().map(Employee::getName);
        namesStream.filter(name->name.length()>3).forEach(System.out::println);
        System.out.println();

        //练习2：
        //Stream调用Stream
        Stream<Stream<Character>> streamStream = list.stream().map(StreamAPITest1::formStringToStream);
        streamStream.forEach(s->s.forEach(System.out::println));
        System.out.println();

//        flatMap(Function f)	接收一个函数作为参数，将流中的每个值都换成另 一个流，然后把所有流连接成一个流
        Stream<Character> characterStream = list.stream().flatMap(StreamAPITest1::formStringToStream);
        characterStream.forEach(System.out::println);

//        mapToDouble(ToDoubleFunction f)	接收一个函数作为参数，该函数会被应用到每个元 素上，产生一个新的 DoubleStream。

//        mapToInt(ToIntFunction f)	接收一个函数作为参数，该函数会被应用到每个元 素上，产生一个新的 IntStream。

//        mapToLong(ToLongFunction f)	接收一个函数作为参数，该函数会被应用到每个元 素上，产生一个新的 LongStream。


    }

    //将字符串中的多个字符构成的集合转换为对应的Stream的方法
    public static Stream<Character> formStringToStream(String str) {
        ArrayList<Character> list = new ArrayList<>();
        for (Character c :
                str.toCharArray()) {
            list.add(c);
        }
        return list.stream();
    }

    @Test
    public void test3() {
        ArrayList list1 = new ArrayList();
        list1.add(1);
        list1.add(2);
        list1.add(3);

        ArrayList list2 = new ArrayList();
        list2.add(4);
        list2.add(5);
        list2.add(6);

//        list1.add(list2);
        list1.addAll(list2);//结果有6个元素，把list2里的所有元素依次添加进了list1
        System.out.println(list1);//结果有4个元素 1 2 3+ list2的整体
    }

    //3.排序
    @Test
    public void test4() {
        //sorted()----自然排序
        List<Integer> list = Arrays.asList(12, 43, 65, 34, 87, 8, -98, 7);
        list.stream().sorted().forEach(System.out::println);

        //抛异常，原因：Employee没有实现Comparable接口
//        List<Employee> employees = EmployeeData.getEmployees();
//        employees.stream().sorted().forEach(System.out::println);

        //sorted(Comparator com)-----定制排序

        List<Employee> employees = EmployeeData.getEmployees();
        employees.stream().sorted((e1, e2) ->{
            int ageValue = Integer.compare(e1.getAge(), e2.getAge());
            //如果年龄相同则比较工资
            if (ageValue != 0) {
                return ageValue;
            } else {
                return -Double.compare(e1.getSalary(), e2.getSalary());
            }
        }).forEach(System.out::println);

    }
}
