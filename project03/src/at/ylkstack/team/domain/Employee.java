package at.ylkstack.team.domain;

/**
 * describe: 职员类
 * @author: ylkstack
 * @Date: 2021/6/3 0:07
 */
public class Employee {
    private int id;//工号
    private String name;//名字
    private int age;//年龄
    private double salary;//薪资

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Employee(){
    }
    public Employee(int id,String name,int age,double salary){
        super();
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
    }
   /**
    * 获得职员信息
    * @Description TODO
    * @Date 2021/6/6 0:25
    * @param
    *@return java.lang.String
    */
    public String getDetails(){
        return id + "\t" + name + "\t" + age + "\t" +salary;
    }
    public String toString(){
        return getDetails();

    }

}
