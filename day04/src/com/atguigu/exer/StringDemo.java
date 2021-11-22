package com.atguigu.exer;

import org.junit.Test;

/**
 * @author: ylkstack  Email:158656887@qq.com
 * @Date: 2021/6/26 22:21
 */
public class StringDemo {
    /*
    2）将一个字符串进行反转。将字符串中指定部分进行反转。比如“abcdefg”反转为”abfedcg”
     */

    //方式一：转换为char[]
    public String reverse(String str,int startIndex,int endIndex){//reverse反转

        if (str != null){
            //        创建一个char[]
            char[] arr = str.toCharArray();
            //x和y分别指向前索引和后索引
            for (int x = startIndex,y = endIndex;x < y;x++,y--){
                //反转交换
                char temp = arr[x];
                arr[x] = arr[y];
                arr[y] = temp;
            }
            //返回值为数组型
            return new String(arr);
        }
        return null;
    }

    //方式二：使用String的拼接
    public String reverse1(String str,int startIndex,int endIndex) {//reverse反转
        if (str != null){

            //第一部分
            String reverseStr = str.substring(0,startIndex);
            //第二部分
            for (int i = endIndex;i >= startIndex;i--){
                reverseStr += str.charAt(i);
            }
            //第三部分
            reverseStr += str.substring(endIndex + 1);
            return reverseStr;
        }
        return null;
    }

    //方式三：使用StringBuffer/StringBuilder替换String
    public String reverse2(String str,int startIndex,int endIndex) {//reverse反转
        if (str != null){

            StringBuilder builder = new StringBuilder(str.length());
            //第一部分 调用append()添加一个以startIndex结尾的字符串
            builder.append(str.substring(0,startIndex));
            //第二部分 增加一个以endIndex逆向开始的字符串
            for (int i = endIndex;i >= startIndex;i--) {
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
        String str = "abcdefg";
        String reverse = reverse2(str, 2, 5);
        System.out.println(reverse);

    }
}
