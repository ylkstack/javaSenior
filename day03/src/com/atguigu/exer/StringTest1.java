package com.atguigu.exer;

import org.junit.Test;

import java.util.Arrays;

/**
 * 1.模拟一个trim方法，去除字符串两端的空格。
 *
 * 2.将一个字符串进行反转。将字符串中指定部分进行反转。比如“abcdefg”反
 * 转为”abfedcg”
 *
 * 3.获取一个字符串在另一个字符串中出现的次数。
 *
 * 比如：获取“ ab”在 “abkkcadkabkebfkabkskab” 中出现的次数
 * @author: ylkstack  Email:158656887@qq.com
 * @Date: 2021/6/22 22:00
 */
public class StringTest1 {
        //1.模拟一个trim方法，去除字符串两端的空格。
        public String mYTrim(String str){
            if (str != null){

                int start = 0;//用于记录从前到后首次索引位置不是空格的索引
                int end = str.length() - 1;//用于记录从后到前首次索引位置不是空格的索引
                while(start < end && str.charAt(start) == ' '){
                    start++;
                }
                while(start < end && str.charAt(end) == ' '){
                    end--;
                }
                if (str.charAt(start) == ' '){
                    return "";
                }
                return str.substring(start, end + 1);
            }

            return null;
        }
    @Test
    public void testMyTrim(){
        String str = "  a  b   c    ";
        String string = mYTrim(str);
        System.out.println("---" + string + "---");
    }

    //  2.将一个字符串进行反转。将字符串中指定部分进行反转。比如“abcdefg”反转为”abfedcg”
    //方法一：转换为char[]
    public String reverse(String str1,int startIndex,int endIndex) {
        if (str1 != null) {

            //转换一个char[]
            char[] charArray = str1.toCharArray();
            //循环x和y分别指向前后索引位置
            for (int x = startIndex, y = endIndex; x < y; x++, y--) {
                char temp = charArray[x];
                charArray[x] = charArray[y];
                charArray[y] = temp;
            }
            //返回值为数组类型
            return new String(charArray);
        }
        return null;
    }
    //方法二：使用String的拼接
    public String reverse1(String str,int startIndex,int endIndex){
            if (str != null){

                //第一部分
                String reverseStr = str.substring(0, startIndex);
                //第二部分，反转部分
                for (int i = endIndex;i >= startIndex;i--){
                    reverseStr += str.charAt(i);
                }
                //第三部分
                reverseStr += str.substring(endIndex + 1);
                return reverseStr;
            }
            return null;
    }

    //方法三：使用StringBuffer和StringBuilder替换String
   public String reverse2(String str,int startIndex,int endIndex){
            if (str != null){

                //创建一个StringBuilder字符串
                StringBuilder builder = new StringBuilder(str.length());
                //第一部分 调用append()添加一个以startIndex结尾的字符串
                builder.append(str.substring(0, startIndex));
                //第二部分 增加一个以endIndex逆向开始的字符串
              for (int i = endIndex;i >= startIndex;i--){
                  builder.append(str.charAt(i));
              }
                //第三部分  增加endIndex以后的字符串
              builder.append(str.substring(endIndex + 1));

              return builder.toString();
            }

            return null;
   }
    @Test
    public void testReverse(){
        String s1 = "abcdefg";
        String reverse = reverse2(s1, 2, 5);
        System.out.println(reverse);
    }

    //  3）获取一个字符串在另一个字符串中出现的次数。
    //      比如：获取“ ab”在 “abkkcadkabkebfkabkskab” 中出现的次数
    public int getCount(String mainStr,String subStr){
            if (mainStr.length() >= subStr.length()){

                int count = 0;//用于记录subStr出现的次数
                int index = 0;//用于记录每次开始索引的位置
                while((index = mainStr.indexOf(subStr, index)) != -1){
                    index += subStr.length();//用来记录每次开始索引的位置
                    count++;
                }
                return count;
            }

        return 0;
    }
    @Test
    public void testGetCount(){
        String s5 = "abkkcadkabkebfkabkskab";
        String s6 = "ab";
        int count = getCount(s5, s6);
        System.out.println(count);
    }

    //  4）获取两个字符串中最大相同子串。比如：
    //   str1 = "abcwerthelloyuiodef“;str2 = "cvhellobnm"
    //   提示：将短的那个串进行长度依次递减的子串与较长的串比较。
    @Test
    public void test4(){
    }


    //  5）对字符串中字符进行自然顺序排序。
    //提示：
    //1字符串变成字符数组。
    //2对数组排序，择，冒泡，Arrays.sort();
    //3将排序后的数组变成字符串。
    @Test
    public void test5(){
        //创建一个字符串
       String s = "abdfgojjeddg";
        //创建一个char[] 来接收字符串底层
        char[] chars = s.toCharArray();
        //对char[]进行排序
        Arrays.sort(chars);
        //将char[] 转换为String
       String s1 = new String(chars);
        System.out.println(s1);
    }
}
