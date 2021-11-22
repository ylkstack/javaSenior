package com.atguigu.java;


import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * 处理流之一：缓冲流的作用
 * 1.缓冲流：
 * BufferedInputStream
 * BufferedOutputStream
 * BufferedReader
 * BufferedWriter
 *
 * 2.缓冲流作用：提高流的读取、写入的速度
 *   提高读写速度的原因：内部提供了缓冲区
 *
 * 3.处理流，就是“套接”在已有流的基础上。
 *
 * @author: ylkstack  Email:158656887@qq.com
 * @Date: 2021/7/31 21:00
 */
public class BufferedTest {
    /*
    实现非文本文件的复制
     */
    @Test
    public void BufferedStreamTest(){
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            //1.造文件
            File srcFile = new File("图片.jpg");
            File destFile = new File("图片3.jpg");

            //2造流
            //2.1 造节点流
            FileInputStream fis = new FileInputStream(srcFile);
            FileOutputStream fos = new FileOutputStream(destFile);
            //2.2 造缓冲流
            bis = new BufferedInputStream(fis);
            bos = new BufferedOutputStream(fos);

            //3.复制的细节：读取、写入
            byte[] buffer = new byte[1024];
            int len;
            while ((len = bis.read(buffer)) != -1){
                bos.write(buffer,0,len);

//                bos.flush();//刷新缓冲区
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4.资源关闭
            //要求：先关闭外层的流，再关闭内层的流
            try {
                if (bos != null){
                    bos.close();
                }
                if (bis != null){
                    bis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            //说明：关闭外层流的同时，内层流也会自动的进行关闭。关于内层流的操作可以省略
//            fos.close();
//            fis.close();
        }

    }

    //实现文件复制的方法
    public void copyFileWithBuffered(String srcPath,String destPath) {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            //1.造文件
            File srcFile = new File("图片.jpg");
            File destFile = new File("图片3.jpg");

            //2造流
            //2.1 造节点流
            FileInputStream fis = new FileInputStream(srcFile);
            FileOutputStream fos = new FileOutputStream(destFile);
            //2.2 造缓冲流
            bis = new BufferedInputStream(fis);
            bos = new BufferedOutputStream(fos);

            //3.复制的细节：读取、写入
            byte[] buffer = new byte[1024];
            int len;
            while ((len = bis.read(buffer)) != -1){
                bos.write(buffer,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4.资源关闭
            //要求：先关闭外层的流，再关闭内层的流
            try {
                if (bos != null){
                    bos.close();
                }
                if (bis != null){
                    bis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            //说明：关闭外层流的同时，内层流也会自动的进行关闭。关于内层流的操作可以省略
//            fos.close();
//            fis.close();
        }
    }
    @Test
    public void testCopyFileWithBuffered(){
        long start = System.currentTimeMillis();
        String srcPath = "C:\\Users\\Administrator\\Desktop\\测试视频\\2_if单选择结构_掷骰子游戏.mp4";
        String destPath = "C:\\Users\\Administrator\\Desktop\\测试视频\\2.1_if单选择结构_掷骰子游戏.mp4";

        copyFileWithBuffered(srcPath,destPath);

        long end = System.currentTimeMillis();
        System.out.println("复制操作花费的时间为：" + (end - start));//复制视频花费的：2毫秒

    }

    /*
    使用BufferedReader和BufferedWriter实现文本文件的复制
     */
    @Test
    public void testBufferedWriter(){
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            //创建文件和相应的流（通过匿名的方式）
            br = new BufferedReader(new FileReader(new File("dbcp.txt")));
            bw = new BufferedWriter(new FileWriter(new File("dbcp1.txt")));

            //读写操作
            //方式一：使用char[]数组
//            char[] cbuf = new char[1024];
//            int len;
//            while ((len = br.read(cbuf)) != -1){
//                bw.write(cbuf,0,len);
//            }

            //方式二：使用String
            String data;
            while ((data = br.readLine()) != null){
                //方法一：不包含换行符
//                bw.write(data+ "\n");//data中不包含换行符
                //方法二：
                bw.write(data);//data中不包含换行符
                bw.newLine();//提供换行的操作
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            if (br != null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bw != null){
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
