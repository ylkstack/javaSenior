package com.atguigu.exer1;

/**
 * 定义一个 Employee 类。
 * 该类包含：private 成员变量 name,age,birthday，其中 birthday 为
 * MyDate 类的对象；
 * 并为每一个属性定义 getter, setter 方法；
 * 并重写 toString 方法输出 name, age, birthday
 * @author: ylkstack  Email:158656887@qq.com
 * @Date: 2021/7/18 21:44
 */
public class Employee implements Comparable{
    private String name;
    private int age;
    private MyDate birthday;

    public Employee(String name, int age, MyDate birthday) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }

    public Employee() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public MyDate getBirthday() {
        return birthday;
    }

    public void setBirthday(MyDate birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                '}';
    }

    @Override
    /*
     * 按照 name 排序方法
     * @Description
     * @Date 2021/7/18 21:56
     * @param o
     * @return int
     */
    public int compareTo(Object o) {
        if (o instanceof Employee){
            Employee e = (Employee) o;
            return this.name.compareTo(e.name);
        }
//        return 0;//因为TreeSet里不能存放返回0的数据。所以return 0之后就不会存储数据
        //或者直接抛异常
        throw new RuntimeException("传入的数据类型不一致");
    }
}
