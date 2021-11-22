package com.atguigu.exer;

import org.junit.Test;

/**
 * @author: ylkstack  Email:158656887@qq.com
 * @Date: 2021/6/27 23:11
 */
public class StringDemo2 {
    /*
    4）获取两个字符串中最大相同子串。比如：
    str1 = "abcwerthelloyuiodef“;str2 = "cvhellobnm"
    提示：将短的那个串进行长度依次递减的子串与较长的串比较。
     */
    public String getMaxSameString(String str1,String str2){
        if (str1 != null && str2 != null){
            //三元判断长度最大字符串
            String maxStr = (str1.length() >= str2.length()) ? str1 : str2;
            //三元判断长度最小字符串
            String minStr = (str1.length() < str2.length()) ? str1 : str2;
            //声明最小数组的长度
            int length = minStr.length();
            for (int i = 0;i < length;i++){
                    //x,y分别代表子字符串的头和尾
                for (int x = 0,y = length - i;y <= length;x++,y++) {
                    String substr = minStr.substring(x, y);
                    //contains()判断minStr是否是maxStr的最长字符串，是返回minStr
                    if (maxStr.contains(substr)) {
                        return substr;
                    }
                }
            }


        }
        return null;
    }
    @Test
    public void testGetMaxSameString(){
        String str1 = "abcwerthelloyuiodef";
        String str2 = "cvhellobnm";
        String maxSameString = getMaxSameString(str1, str2);
        System.out.println(maxSameString);
    }
}
