package com.atguigu.java;

import org.junit.Test;

import javax.imageio.stream.ImageInputStream;
import java.time.*;
import java.util.Date;

/**
 * jdk 8 中日期时间API的测试
 *
 * LocalDate代表IOS格式（yyyy-MM-dd）的日期,可以存储 生日、纪念日等日期。
 * LocalTime表示一个时间，而不是日期。
 * LocalDateTime是用来表示日期和时间的，这是一个最常用的类之一。
 * @author: ylkstack  Email:158656887@qq.com
 * @Date: 2021/6/30 22:02
 */
public class JDK8DateTimeTest {
    @Test
    public void testDate(){
        Date date1 = new Date(2020 - 1900,9 - 1,8);
        //有偏移量，年份从 1900开始 需要减去1900  月份从0开始，需要 -1
        System.out.println(date1);//结果：Fri Oct 08 00:00:00 CST 3920
    }

    /*
    LocalDate、  LocalTime、LocalDateTime 的使用说明：
        1.LocalDateTime相较于LocalDate、LocalTime使用频率更高
        2.类似于Calendar
    LocalDate代表IOS格式（yyyy-MM-dd）的日期,可以存储 生日、纪念日等日期。
    LocalTime表示一个时间，而不是日期。
    LocalDateTime是用来表示日期和时间的，这是一个最常用的类之一。

     */
    @Test
    public void test1(){

        //now():获取当前的日期、时间、日期加时间
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();

        System.out.println(localDate);
        System.out.println(localTime);
        System.out.println(localDateTime);

        //of():设置指定的年月日时分秒，是没有偏移量的
        LocalDateTime localDateTime1 = LocalDateTime.of(2020, 10, 6, 13, 23, 43);
        System.out.println(localDateTime1);

        //getXxx()；获取相关的属性
        System.out.println(localDateTime.getDayOfMonth());
        System.out.println(localDateTime.getDayOfWeek());
        System.out.println(localDateTime.getMonth());
        System.out.println(localDateTime.getMonthValue());
        System.out.println(localDateTime.getMinute());

        //体现不可变性
        //withXxx():设置相关操作
        LocalDate localDate1 = localDate.withDayOfMonth(22);
        System.out.println(localDate);//2021-06-30
        System.out.println(localDate1);//2021-06-22

        LocalDateTime localDateTime2 = localDateTime.withHour(4);
        System.out.println(localDateTime);//2021-06-30T23:15:59.191
        System.out.println(localDateTime2);//2021-06-30T04:15:59.191

        //仍然体现不可变性

        LocalDateTime localDateTime3 = localDateTime.plusMonths(3);
        System.out.println(localDateTime3);//2021-09-30T23:18:05.322

        LocalDateTime localDateTime4 = localDateTime.minusDays(6);
        System.out.println(localDateTime);//2021-06-30T23:19:47.360
        System.out.println(localDateTime4);//2021-06-24T23:19:47.360
    }
    /*
    Instant的使用
        类似于java.util.Date类
     */
    @Test
    public void test2(){
        //now():获取本初子午线对应的时间
        Instant instant = Instant.now();
        System.out.println(instant);//此处时间为“本初子午线”时间：2021-06-30T15:33:18.237Z

        //添加时间的偏移量
        OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.ofHours(8));//东八区+8小时
        System.out.println(offsetDateTime);//2021-06-30T23:36:58.254+08:00

        //获取自1970年01月01日00时00分00秒（UTC）开始的毫秒数--->Date类的getTime()
        long milli = instant.toEpochMilli();
        System.out.println(milli);//毫秒数 1625067814873（时间戳）

        //ofEpochMilli():通过给定的毫秒数，获取Instant实例--->Date(long millis)
        Instant instant1 = Instant.ofEpochMilli(1625067814873L);
        System.out.println(instant1);

    }

    @Test
    public void teste(){
        Date date = new Date();
        System.out.println(date.toString());
        System.out.println(date.getTime());
        java.sql.Date date1 = new java.sql.Date(date.getTime());
        System.out.println(date1);
    }
}
