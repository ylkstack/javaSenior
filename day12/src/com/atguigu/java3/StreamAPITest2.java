package com.atguigu.java3;

import com.atguigu.java2.Employee;
import com.atguigu.java2.EmployeeData;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: ylkstack  Email:158656887@qq.com
 * @Date: 2021/8/24 0:01
 */
public class StreamAPITest2 {
    //1.匹配操作
    @Test
    public void test1() {
        List<Employee> employees = EmployeeData.getEmployees();
//        allMatch(Predicate p)	检查是否匹配所有元素
//        练习：是否所有的员工的年龄都大于18
        boolean allMatch = employees.stream().allMatch(e -> e.getAge() > 18);
        System.out.println(allMatch);
//        anyMatch(Predicate p)	检查是否至少匹配一个元素
//        练习：是否存在员工的工资大于1000
        boolean anyMatch = employees.stream().anyMatch(e -> e.getSalary() > 10000);
        System.out.println(allMatch);
//        noneMatch(Predicate	p)	检查是否没有匹配所有元素
//        练习：是否存在员工的姓“雷”
        //该方法为是否没有，如果存在则返回false  如果不存在则返回true
        boolean noneMatch = employees.stream().noneMatch(e -> e.getName().startsWith("雷"));
        System.out.println(noneMatch);
//        findFirst()	返回第一个元素
        Optional<Employee> employee = employees.stream().findFirst();
        System.out.println(employee);
//        findAny()	返回当前流中的任意元素
//        parallelStream()----并行流状态的
//        Stream-----串行流状态
        Optional<Employee> employee1 = employee.stream().parallel().findAny();
        System.out.println(employee1);

    }

    @Test
    public void test2() {
        List<Employee> employees = EmployeeData.getEmployees();
        //        count()	返回流中元素总数
        long count = employees.stream().filter(e -> e.getSalary() > 5000).count();
        System.out.println(count);
//        max(Comparator c)	返回流中最大值
//        练习：返回最高的工资：
        Stream<Double> salaryStream = employees.stream().map(e -> e.getSalary());
        Optional<Double> maxSalay = salaryStream.max(Double::compare);
        System.out.println(maxSalay);
//        min(Comparator c)	返回流中最小值
//        练习：返回最低工资的员工
//        employees.stream().min((employees1, employees2) -> Double.compare(employees1.getSalary(), employees2.getSalary()));
        //方法的引用的写法
        Optional<Employee> employee = employees.stream().min(Comparator.comparingDouble(Employee::getSalary));
        System.out.println(employee);
        System.out.println();
        //        forEach(Consumer c)	内部迭代(使用 Collection 接口需要用户去做迭代 称为外部迭代。
//        相反，Stream API 使用内部迭 代——它帮你把迭代做了)
        employees.stream().forEach(System.out::println);
        //使用集合的遍历方法
        employees.forEach(System.out::println);
    }

    //2-归约
    @Test
    public void test3() {

//        reduce(T iden, BinaryOperator b)	可以将流中元素反复结合起来，得到一 个值。返回 T
//        练习1：计算1~10的自然数的和
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        Integer sum = list.stream().reduce(0, Integer::sum);//此处的0为初始值。可以改成其他的为从此数字开始累加
        System.out.println(sum);

//        reduce(BinaryOperator b)	可以将流中元素反复结合起来，得到一个值。返回 Optional<T>
//        练习2：计算公司所有员工工资的总和
        List<Employee> employees = EmployeeData.getEmployees();
        Stream<Double> salaryStream = employees.stream().map(Employee::getSalary);
        //Lmbda表达式写法
//        Optional<Double> sumMoney = salaryStream.reduce(Double::sum);
        //普通这样写
        Optional<Double> sumMoney = salaryStream.reduce((d1,d2)->d1+d2);
        System.out.println(sumMoney);
    }

    //3.收集
    @Test
    public void test4() {

//        collect(Collector c)	将流转换为其他形式。接收一个 Collector接口的实现，
//        用于给Stream中元素做汇总 的方法
//        练习1：查找工资大于6000的员工，结果返回为一个list或Set
        //返回list
        List<Employee> employees = EmployeeData.getEmployees();
        List<Employee> employeeList = employees.stream().filter(e -> e.getSalary() > 6000).collect(Collectors.toList());
        employeeList.forEach(System.out::println);
        System.out.println();
        //返回Set
        Set<Employee> employeeSet = employees.stream().filter(e -> e.getSalary() > 6000).collect(Collectors.toSet());
        employeeSet.forEach(System.out::println);
    }
}
