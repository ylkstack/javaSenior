package com.atguigu.java;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
import java.util.Date;

/**
 * jdk 8之前的日期时间的API测试
 * 1、 System类中currentTimeMillis();
 * 2、 java.util.Date和子类java.sql.Date;
 * 3、 SimpleDateFormat
 * 4、 Colendar
 * @author: ylkstack  Email:158656887@qq.com
 * @Date: 2021/6/28 22:54
 */
public class DateTimeTest {
    /*
    SimpleDateFormae的使用：SimpleDateFormat对日期Date类的格式化和解析

    1.两个操作：
    1.1 格式化：日期--->字符串
    1.2 解析：格式化的逆过程，字符串--->日期    解析时一定要和对象的格式一样

    2.SimpleDateFormae的实例化
     */
    @Test
    public void testSimpleDateFormat() throws ParseException {
        //实例化SimpleDateFormat:使用默认的构造器
        SimpleDateFormat sdf = new SimpleDateFormat();

        //格式化：日期---字符串
        Date date = new Date();
        System.out.println(date);//结果为英文，不易看懂，需要格式化为字符串

        String format = sdf.format(date);
        System.out.println(format);

        //解析：格式化的逆过程，字符串--->日期    解析时一定要和对象的格式一样
        String str ="21-6-28 下午11:25";
        Date date1 = sdf.parse(str);
        System.out.println(date1);

        //**********按照指定的方式格式化和解析：调用带参的构造器***********
//        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyy.MMMMM.dd GGG hh:mm aaa");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        //格式化
        String format1 = sdf1.format(date);
        System.out.println(format1);
        //解析:解析时一定要和对象的格式一样
        //要求字符串必须是负荷SimpleDateFormat识别的格式（通过构造器参数体现），否则异常
        Date parse = sdf1.parse("2021-06-28 11:31:00");
        System.out.println(parse);
    }

    /*
    练习一："字符串2020-09-08"转换为java.slq.Date
    练习二："三天打渔两天晒网"  1990-01-01   xxxx-xx-xx 打渔？晒网？

    举例：2020-09-08？ 需要计算出来从当前实到到1990-01-01的总天数
    总天数 % 5 == 1，2，3 ：打渔
    总天数 % 5 == 4 ，0 ：晒网

    总天数计算。
    方式一： (date2.getTime() - date1.getTime())/(1000*60*60*24) + 1=总天数
    方式二： 1990-01-01 --->2019-12-31 + 2020-01-01-->2020-09-08
     */
    @Test
    public void testExer() throws ParseException {
        String birth = "2020-09-08";

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = sdf1.parse(birth);
        System.out.println(parse);
        java.sql.Date birthDate = new java.sql.Date(parse.getTime());
        System.out.println(birthDate);
    }

    /*
    Calendar 日历类(抽象类)的使用
     */
    @Test
    public void Calendar(){
        //1.实例化
        //方式一：创建其子类（GregorionCalendar）的对象
        //方式二：调用其静态方法getInstance()
        Calendar calendar = Calendar.getInstance();
//        System.out.println(calendar.getClass());//java.util.GregorianCalendar

        //2.常用方法
        //get()//获取当前第几天
        int days = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println(days);
        System.out.println(calendar.get(Calendar.DAY_OF_YEAR));
        //set()//设置天数
        //体现Calendar的可变性,会改变原有的日期
        calendar.set(Calendar.DAY_OF_MONTH, 22);
        days = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println(days);
        //add()  //增加天数，如果是减少几天就把amount参数设置为 负数
        calendar.add(Calendar.DAY_OF_MONTH,3);
        days = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println(days);
        //getTime():日历类-->Date
        Date date = calendar.getTime();
        System.out.println(date);
        //setTime():Date--->日历类
        Date date1 = new Date();
        calendar.setTime(date1);
        days = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println(days);

    }

    /*
    DateTimeFormatter :格式化或解析日期、时间
    类似于SimpleDateFormat
     */
    @Test
    public void test3(){
//        ofPattern(String pattern)	静态方法， 返回一个指定字符串格式 的DateTimeFormatter
//        format(TemporalAccessor t)	格式化一个日期、时间，返回字符串
//        parse(CharSequence text)	将指定格式的字符序列解析为一个日期、时间


//        方式一：预定义的标准格式。如：ISO_LOCAL_DATE_TIME;ISO_LOCAL_DATE;ISO_LOCAL_TIME
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        //格式化:日期--->字符串
        LocalDateTime localDateTime = LocalDateTime.now();
        String str1 = formatter.format(localDateTime);
        System.out.println(localDateTime);
        System.out.println(str1);//2021-07-01T20:27:28.605

        //解析：字符串--->日期
        TemporalAccessor parse = formatter.parse("2021-07-01T20:27:28.605");
        System.out.println(parse);

//        方式二：本地化相关的格式。如：ofLocalizedDateTime(FormatStyle.LONG)
        //本地化相关的格式：如：ofLocalizedDateTime()
        //FormatStyle.LONG / FormatStyle.MEDIUM / FormatStyle.SHORT :适用于LocalDateTime
        //FormatStyle.SHORT : 21-7-1 下午8:40
        //FormatStyle.LONG : 2021年7月1日 下午08时41分48秒
        //FormatStyle.MEDIUM : 2021-7-1 20:42:29
        DateTimeFormatter formatter1 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        //格式化：
        String str2 = formatter1.format(localDateTime);
        System.out.println(str2);//21-7-1 下午8:40

        //本地化相关的格式：如：ofLocalizedDate()
        //FormatStyle.FULL / FormatStyle.LONG / FormatStyle.MEDIUM / FormatStyle.SHORT:适用于LocalDate
        //FormatStyle.FULL : 2021年7月1日 星期四
        //FormatStyle.MEDIUM : 2021-7-1
        //FormatStyle.LONG : 2021年7月1日
        //FormatStyle.SHORT : 21-7-1
        DateTimeFormatter formatter2 = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
        //格式化
        String str3 = formatter2.format(LocalDate.now());
        System.out.println(str3);

//      重点：  方式三：自定义的格式。如：ofPattern(“yyyy-MM-dd hh:mm:ss”)
        DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        //格式化
        String str4 = formatter3.format(LocalDateTime.now());
        System.out.println(str4);//2021-07-01 08:52:11

        //解析
        TemporalAccessor parse1 = formatter3.parse(str4);
        System.out.println(parse1);

    }
}
