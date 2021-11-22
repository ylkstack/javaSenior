package com.atguigu.java;

import org.junit.Test;

/**
 * @author: ylkstack  Email:158656887@qq.com
 * @Date: 2021/6/28 22:31
 */
public class IDEADebug {
    @Test
    public void testStringBuffer(){

        String str = null;
        StringBuffer sb = new StringBuffer();
        sb.append(str);
        System.out.println(sb.length());//4
        System.out.println(sb);//"null"

        StringBuffer sb1 = new StringBuffer(str);//抛异常
        System.out.println(sb1);//
    }

}
