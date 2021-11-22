package com.atguigu.java;


import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * @author: ylkstack  Email:158656887@qq.com
 * @Date: 2021/7/31 10:57
 */
public class ceshi {
    @Test
    public void test() throws IOException {
        FileInputStream fi = null;
        FileOutputStream fo = null;
        try {
            //1.创建File类的对象，指明读入和写出的文件
            File srcjpg = new File("图片.jpg");
            File destjpg = new File("图片1.jpg");

            //2.创建输入流和输出流的对象
            fi = new FileInputStream(srcjpg);
            fo = new FileOutputStream(destjpg);
            //3.数据的读入和写出操作
            byte[] b = new byte[5];
            int len;
            while ((len = fi.read(b)) != -1){
                fo.write(b,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4.关闭输入输出流
            try {
                if (fi != null)
                fi.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fo != null)
                fo.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
