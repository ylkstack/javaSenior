package com.atguigu.exer;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author: ylkstack  Email:158656887@qq.com
 * @Date: 2021/7/31 22:26
 */
public class PicTest {
    //图片的加密
    @Test
    public void test1(){
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream("图片.jpg");
            fos = new FileOutputStream("图片secret.jpg");

            byte[] buffer = new byte[20];
            int len;
            while ((len = fis.read(buffer)) != -1) {
                //字节数组进行修改
                //错误的
    //            for (byte b :
    //                    buffer) {
    //                b = (byte) (b ^ 5);
    //            }
                //正确的
                for (int i = 0; i < len; i++) {
                    buffer[i] =(byte) (buffer[i] ^ 5);
                }

                fos.write(buffer,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    @Test
    public void test2(){
        //图片的加密
            FileInputStream fis = null;
            FileOutputStream fos = null;
            try {
                fis = new FileInputStream("图片secret.jpg");
                fos = new FileOutputStream("图片4.jpg");

                byte[] buffer = new byte[20];
                int len;
                while ((len = fis.read(buffer)) != -1) {
                    //字节数组进行修改
                    //错误的
                    //            for (byte b :
                    //                    buffer) {
                    //                b = (byte) (b ^ 5);
                    //            }
                    //正确的
                    for (int i = 0; i < len; i++) {
                        buffer[i] =(byte) (buffer[i] ^ 5);
                    }

                    fos.write(buffer,0,len);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
    }
}
