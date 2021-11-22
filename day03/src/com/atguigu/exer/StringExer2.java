package com.atguigu.exer;

import org.junit.Test;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.text.ParseException;

/**
 * @author: ylkstack  Email:158656887@qq.com
 * @Date: 2021/7/2 12:30
 */
public class StringExer2 {
    /*
    1．请编写一个 Application 实现如下功能：接受命令行中给出的一个字母串，先将该串原样输出，
    然后判断该串的第一个字母是否为大写，若是大写则统计该串中大写字母的个数，并将所有大写字母输出；
    否则输出信息串”第一个字母不是大写字母!”。
     */
    public String Application(String str){
        int count = 0;//用来记录大写字母个数
         if (str.length() != 0){
            System.out.println(str);
            if ((int)str.charAt(0) >= 65 && (int)str.charAt(0) <= 90){
                for (int i = 0; i < str.length(); i++) {
                    if (str.charAt(i) >= 65 && str.charAt(i) <= 90){
                        System.out.println(str.charAt(i));
                        count++;
                    }
                }
                System.out.println();
                return "共有" + count + "个大写字母。";
            }else
                return "第一个字母不是大写字母";
        }else
            return "请先为字符串赋值";
    }
    @Test
    public void testApplication(){
        String str = Application("AdfdlkejkdAJhKJkijK");
        System.out.println(str);
    }


    /*
    一个应用程序，接受用户输入的一行字符串，统计字符个数，然后反序输出。
     */
    @Test
    public void test2(){
        String str = "kejkdpojlkjf";
        int j = str.length();
        System.out.println(str + " " + j);
        for (int i = j-1; i >= 0; i--) {
            System.out.print(str.charAt(i) );
        }

    }


    /*
    模拟一个trim方法，去除字符串两端的空格。
     */
    public String myTrim(String str){
        int start = 0;//用来记录从前往后首次索引位置不是空格的的索引位置
        int end = str.length() - 1;//用来记录从后往前首次索引位置不是空格的的索引位置
        while(start < end && str.charAt(start) == ' '){
            start++;
        }
        while (start < end && str.charAt(end) == ' '){
            end--;
        }
        if (start == end){
            return "";
        }
        return str.substring(start, end + 1);
    }
    @Test
    public void testTrim(){
        String s = myTrim("  dfed ddd ffeg df  ");
        System.out.println(s);
    }


    /*
    将一个字符串进行反转。将字符串中指定部分进行反转。比如将“abcdefg”反转为”abfedcg”
    拓展：I am a student!   写一个方法：实现输出 !student a am I
     */
    //方式一：
    public String reverseString(String str,int start,int end){
        char[] chars = str.toCharArray();//字符串---->专字符串数组
        for (int i = start,j = end; i < j; i++,j--) {
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
        }
        return new String(chars);

    }
    //方式二：
    public String reverseString1(String str,int start,int end){

        if (str != null){

            // 1.定义一个长度和str相同的StringBuffer字符串
            StringBuffer stringBuffer = new StringBuffer(str.length());
            stringBuffer.append(str.substring(0, start));//代表start以前的字符串
            for (int i = end ; i >= start; i--) {
                stringBuffer.append(str.charAt(i));
            }
            stringBuffer.append(str.substring(end + 1));
            return stringBuffer.toString();
        }
        return null;
    }
    @Test
    public void testReverseString(){
        String str = reverseString1("fgherfdg", 2, 5);
        System.out.println(str);
    }

    /*
    获取一个字符串在另一个字符串中出现的次数。判断str2在str1中出现的次数
     */
    public int getCount(String mainStr, String subStr) {
        if (mainStr.length() >= subStr.length()) {
            int count = 0;
            int index = 0;
            // while((index = mainStr.indexOf(subStr)) != -1){
            // count++;
            // mainStr = mainStr.substring(index + subStr.length());
            // }
            // 改进：
            while ((index = mainStr.indexOf(subStr, index)) != -1) {
                index += subStr.length();
                count++;
            }

            return count;
        } else {
            return 0;
        }

    }

    @Test
    public void testGetCount(){
        int time = getCount("abccabcabcabcabc", "ab");
        System.out.println(time);
    }
    /*
    获取两个字符串中最大相同子串。
     */
    public String getMaxSameSubString(String str1, String str2) {
        if (str1 != null && str2 != null) {
            String maxStr = (str1.length() > str2.length()) ? str1 : str2;
            String minStr = (str1.length() > str2.length()) ? str2 : str1;

            int len = minStr.length();

            for (int i = 0; i < len; i++) {// 0 1 2 3 4 此层循环决定要取几个字符

                for (int x = 0, y = len - i; y <= len; x++, y++) {

                    if (maxStr.contains(minStr.substring(x, y))) {

                        return minStr.substring(x, y);
                    }

                }

            }
        }
        return null;
    }

    // 如果存在多个长度相同的最大相同子串
    // 此时先返回String[]，后面可以用集合中的ArrayList替换，较方便
    public String[] getMaxSameSubString1(String str1, String str2) {
        if (str1 != null && str2 != null) {
            StringBuffer sBuffer = new StringBuffer();
            String maxString = (str1.length() > str2.length()) ? str1 : str2;
            String minString = (str1.length() > str2.length()) ? str2 : str1;

            int len = minString.length();
            for (int i = 0; i < len; i++) {
                for (int x = 0, y = len - i; y <= len; x++, y++) {
                    String subString = minString.substring(x, y);
                    if (maxString.contains(subString)) {
                        sBuffer.append(subString + ",");
                    }
                }
                System.out.println(sBuffer);
                if (sBuffer.length() != 0) {
                    break;
                }
            }
            String[] split = sBuffer.toString().replaceAll(",$", "").split("\\,");
            return split;
        }

        return null;
    }
    // 如果存在多个长度相同的最大相同子串：使用ArrayList
//	public List<String> getMaxSameSubString1(String str1, String str2) {
//		if (str1 != null && str2 != null) {
//			List<String> list = new ArrayList<String>();
//			String maxString = (str1.length() > str2.length()) ? str1 : str2;
//			String minString = (str1.length() > str2.length()) ? str2 : str1;
//
//			int len = minString.length();
//			for (int i = 0; i < len; i++) {
//				for (int x = 0, y = len - i; y <= len; x++, y++) {
//					String subString = minString.substring(x, y);
//					if (maxString.contains(subString)) {
//						list.add(subString);
//					}
//				}
//				if (list.size() != 0) {
//					break;
//				}
//			}
//			return list;
//		}
//
//		return null;
//	}


    /*
    对字符串中字符进行自然顺序排序
     */
    @Test
    public void testSort() {
        String str = "abcwerthelloyuiodef";
        char[] arr = str.toCharArray();
        Arrays.sort(arr);

        String newStr = new String(arr);
        System.out.println(newStr);
    }

}

/*
    中国有句俗语叫“三天打鱼两天晒网”。如果从1990年1月1日起开始执行“三天打鱼两天晒网”。
    如何判断在以后的某一天中是“打鱼”还是“晒网”？
 */
class GetFish {

    public static void main(String[] args) {
        String date1 = "1990/1/1"; // 开始“三天打鱼两天晒网”的日期
        String date2 = "1990/1/10"; // 手动输入的日期
        long day = getQuot(date1, date2);// 传入值计算时间差
        if (day % 5 == 0 || day % 5 == 4) {
            System.out.println("今天是休息日，可以晒晒网");
        } else {
            System.out.println("今天要工作，打鱼了！");
        }

    }

    public static long getQuot(String time1, String time2) {
        long dayDistance = 0;
        //格式化日期
        SimpleDateFormat ft = new SimpleDateFormat("yyyy/MM/dd");
        try {
            Date date1 = ft.parse(time1);
            Date date2 = ft.parse(time2);
            dayDistance = date2.getTime() - date1.getTime();
            dayDistance = dayDistance / 1000 / 60 / 60 / 24 + 1;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dayDistance;
    }

}