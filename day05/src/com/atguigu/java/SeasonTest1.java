package com.atguigu.java;

/**
 * 使用enum关键字定义枚举类
 * 说明：定义的枚举类默认继承于 class java.lang.Enum类
 *
 * 枚举类中toString() 默认输出为枚举对象名。因此不需要重写toString() 如有特殊诉求可以重写
 * uthor: ylkstack  Email:158656887@qq.com
 * @Date: 2021/7/3 23:55
 */
public class SeasonTest1 {
    public static void main(String[] args) {
        Season1 summer = Season1.SUMMER;
        //toString()
        System.out.println(summer.toString());//SUMMER  默认输出结果为 对象名

//        System.out.println(Season1.class.getSuperclass());//class java.lang.Enum
        System.out.println("**************");

        /*
         values()方法：返回枚举类型的对象数组。该方法可以很方便地遍历所有的枚举值。
         valueOf(String str)：可以把一个字符串转为对应的枚举类对象。要求字符 串必须是枚举类对象的“名字”。如不是，会有运行时异常：  IllegalArgumentException。
         toString()：返回当前枚举类对象常量的名称
         */
        //values():返回枚举类型的对象数组。该方法可以很方便地遍历所有的枚举值。
        Season1[] values = Season1.values();//此处数组里储存的是Season1里的属性
        for (int i = 0; i < values.length; i++) {
            System.out.println(values[i]);
            values[i].show();
        }

        System.out.println("**************");
        Thread.State[] values1 = Thread.State.values();
        for (int i = 0; i < values.length; i++) {
            System.out.println(values1[i]);
        }

        //valueOf(String objName):返回枚举类中对象名是objName的对象
        Season1 winter = Season1.valueOf("WINTER");
//        Season1 winter = Season1.valueOf("WINTER1");
        //如果没有objName的枚举类对象，则抛异常：IllegalArgumentException
        System.out.println(winter);
        winter.show();
    }
}

interface Info{
    void show();
}
 //自定义枚举类
 enum Season1 implements com.atguigu.java.Info {
     //1.提供当前枚举类的对象,多个对象之间用“，”逗号隔开，末尾的用“;”英文分号结束
//     情况二：让枚举类的对象分别实现接口中的抽象方法
     SPRING("春天", "出暖花开"){
         @Override
         public void show() {
             System.out.println("春天在哪里？");
         }
     },
     SUMMER("夏天", "夏日炎炎"){
         @Override
         public void show() {
             System.out.println("宁夏");
         }
     },
     AUTUMN("秋天", "秋高气爽"){
         @Override
         public void show() {
             System.out.println("秋天不回来");
         }
     },
     WINTER("冬天", "冰天雪地"){
         @Override
         public void show() {
             System.out.println("大约在冬季");
         }
     };

     //2.声明Season1对象的属性:private final修饰
     private final String SeasonName;
     private final String SeasonDesc;
     //3.私有化类的构造器，并给对象属性赋值
    private Season1(String SeasonName,String SeasonDesc){
         this.SeasonName = SeasonName;
         this.SeasonDesc = SeasonDesc;
     }

    //4.其他诉求： 获取枚举类对象的属性


    public String getSeasonName() {
        return SeasonName;
    }

    public String getSeasonDesc() {
        return SeasonDesc;
    }


     //4.其他诉求：提供 toString()

//    @Override
//    public String toString() {
//        return "Season1{" +
//                "SeasonName='" + SeasonName + '\'' +
//                ", SeasonDesc='" + SeasonDesc + '\'' +
//                '}';
//    }

//     情况一：实现接口，在enum类中实现抽象方法
//    @Override
//    public void show() {
//        System.out.println("这是一个季节");
//    }
}