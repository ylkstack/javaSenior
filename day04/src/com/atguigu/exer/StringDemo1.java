package com.atguigu.exer;

import org.junit.Test;

/**
 * @author: ylkstack  Email:158656887@qq.com
 * @Date: 2021/6/26 23:24
 */
public class StringDemo1 {
    /*
    3）获取一个字符串在另一个字符串中出现的次数。
      比如：获取“ ab”在 “abkkcadkabkebfkabkskab” 中出现的次数
     */
    /**
     * 获取subStr在mainStr中出现的次数
     * @Description
     * @Date 2021/6/26 23:25
     * @param mainStr
     * @param subStr
     * @return int
     */
    public int getCount(String mainStr,String subStr){
        //声明mainStr字符串的长度
        int mainLength = mainStr.length();
        //声明subStr字符串的长度
        int subLength = subStr.length();
        //记录count次数
        int count = 0;
        int index = 0;
        if (mainLength >= subLength){
            //方式一：
//            while ((index = mainStr.indexOf(subStr)) != -1){
//                count++;
//                mainStr = mainStr.substring(index + subStr.length());
//            }
            //方式二：对方式一的改进
            while((index = mainStr.indexOf(subStr, index)) != -1){
                index += subStr.length();
                count++;
            }
            return count;
        }else
            return 0;
    }
    @Test
    public void testGetCount(){
        String string = "abkkcadkabkebfkabkskab";
        String sunString = "ab";
        int count = getCount(string, sunString);
        System.out.println(count);
    }
}
