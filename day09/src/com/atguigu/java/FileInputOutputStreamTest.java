package com.atguigu.java;

import org.junit.Test;

import java.io.*;

/**
 * 测试FileInputStream和FileOutputStream的使用
 *
 * 结论：
 * 1.对于文本文件(.txt,.java,.c,.cpp)，使用字符流来处理
 * 2.对于非文本文件(.jpg,.mp3,.mp4,.avi,.doc,.ppt,...)，使用字节流处理
 *
 * @author: ylkstack  Email:158656887@qq.com
 * @Date: 2021/7/31 11:11
 */
public class FileInputOutputStreamTest {
    @Test
    public void testFileInputStream( ) throws FileNotFoundException {
        FileInputStream fis = null;
        try {
            //1.造文件
            File file = new File("hello.txt");
            //2.造流
            fis = new FileInputStream(file);

            //3.读数据
            byte[] buffer = new byte[5];
            int len;//记录每次读取的字节的个数
            while ((len = fis.read(buffer)) != -1){
                String string = new String(buffer, 0, len);
                System.out.print(string);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4.关闭资源
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /*
    实现对图谱的复制操作
     */
    @Test
    public void testFileInputOutputStream(){
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            //
            File srcFile = new File("图片.jpg");
            File destFile = new File("图片1.jpg");

            //
            fis = new FileInputStream(srcFile);
            fos = new FileOutputStream(destFile);

            //复制的过程
            byte[] buffer = new byte[5];
            int len;
            while ((len = fis.read(buffer)) != -1){
                fos.write(buffer,0,len);
            }
            System.out.println("复制成功");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            try {
                if (fis != null)
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fos != null)
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    //指定路径下的文件的复制
    public void copyFile(String srcPath,String destPath){
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            //
            File srcFile = new File(srcPath);
            File destFile = new File(destPath);

            //
            fis = new FileInputStream(srcFile);
            fos = new FileOutputStream(destFile);

            //复制的过程
            byte[] buffer = new byte[1024];//1024=2的十次方
            int len;
            while ((len = fis.read(buffer)) != -1){
                fos.write(buffer,0,len);
            }
            System.out.println("复制成功");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            try {
                if (fis != null)
                    fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fos != null)
                    fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testCopyFile(){
        long start = System.currentTimeMillis();
//        String srcPath = "C:\\Users\\Administrator\\Desktop\\测试视频\\2_if单选择结构_掷骰子游戏.mp4";
//        String destPath = "C:\\Users\\Administrator\\Desktop\\测试视频\\2.1_if单选择结构_掷骰子游戏.mp4";

        String srcPath = "hello.txt";
        String destPath = "hello3.txt";
        copyFile(srcPath,destPath);

        long end = System.currentTimeMillis();
        System.out.println("复制操作花费的时间为：" + (end - start));//复制视频花费的：1890毫秒

    }
}
