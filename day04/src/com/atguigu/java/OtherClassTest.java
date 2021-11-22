package com.atguigu.java;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * 其他常用类的使用
 * 1.System
 * 2.Math
 * 3.BigInteger 和 BingDecimal
 * @author: ylkstack  Email:158656887@qq.com
 * @Date: 2021/7/2 22:30
 */
public class OtherClassTest {
    @Test
    public void test1(){
        //java运行时环境版本
        String javaVersion = System.getProperty("java.version");
        System.out.println("java的version:" + javaVersion);

        //java安装目录
        String javaHome = System.getProperty("java.home");
        System.out.println("java的home:" + javaHome);
        //操作系统的名称
        String osName = System.getProperty("os.name");
        System.out.println("os的name:" + osName);

        //操作系统的版本
        String osVersion = System.getProperty("os.version");
        System.out.println("os的version:" + osVersion);

        //用户的账户名称
        String userName = System.getProperty("user.name");
        System.out.println("user的name:" + userName);

        //用户的主目录
        String userHome = System.getProperty("user.home");
        System.out.println("user的home:" + userHome);
        //用户的当前工作目录
        String userDir = System.getProperty("user.dir");
        System.out.println("user的dir:" + userDir);
    }

    @Test
    public void test2(){
            BigInteger bi = new BigInteger("124332411215465651346551245445127");
            BigDecimal bd = new BigDecimal("12435.351");
            BigDecimal bd2 = new BigDecimal("11");
            System.out.println(bi);
//          System.out.println(bd.divide(bd2));//当除不尽时，又未指定多少位精度，会报错
        // 除
          System.out.println(bd.divide(bd2, BigDecimal.ROUND_HALF_UP));
          System.out.println(bd.divide(bd2, 25, BigDecimal.ROUND_HALF_UP));
//        public static final int ROUND_HALF_UP 四舍五入模式向“最近邻居”转弯，
//        除非两个邻居都是等距的，在这种情况下是圆括弧的。 对于ROUND_UP如果丢弃的分数为0.5，
//        则表现为; 否则，表现为ROUND_DOWN 。 请注意，这是我们大多数人在小学教学的舍入模式。
    }
}
