package com.atguigu.java;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * RandomAccessFile的使用
 * 1.RandomAccessFile声明在java.io包下，但直接继承与java.lang.Object类，实现了DataInput和DataOutput接口
 * 2.RandomAccessFile即可作为一个输入流，又可以作为一个输出流（需造两个对象）
 *
 * 3.RandomAccessFile作为输出流时，写出到的文件如果不存在，则在执行过程中自动创建，
 *   如果写出到的文件存在，则会对原有文件内容进行覆盖，（默认是从头覆盖）
 *
 * 4. 可以通过相关的操作，实现RandomAccessFile“插入”数据的效果
 *
 * @author: ylkstack  Email:158656887@qq.com
 * @Date: 2021/8/3 23:04
 */
public class RandomAccessFileTest {
    @Test
    public void test1(){
        RandomAccessFile raf1 = null;
        RandomAccessFile raf2 = null;
        try {
            //读取的
            raf1 = new RandomAccessFile("图片.jpg","r");
            //输出的
            raf2 = new RandomAccessFile("图片1.jpg","rw");

            //读写过程
            byte[] buffer = new byte[1024];
            int len;
            while ((len = raf1.read(buffer)) != -1) {
                raf2.write(buffer,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (raf1 != null) {
                try {
                    raf1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (raf2 != null) {
                try {
                    raf2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void test2() throws IOException {
        RandomAccessFile raf1 = new RandomAccessFile("hello.txt","rw");

        raf1.seek(3);//将指针调到脚标为3的位置
        raf1.write("xyz".getBytes());//此时是覆盖效果

        raf1.close();
    }
    /*
    使用RandomAccessFile实现数据的插入效果
     */
    @Test
    public void test3(){
        RandomAccessFile raf1 = null;
        try {
            raf1 = new RandomAccessFile("hello.txt","rw");

            raf1.seek(3);//将指针调到脚标为3的位置
            //保存指针3后面的所有数据到StringBuilder中
            StringBuilder builder = new StringBuilder((int) new File("hello.txt").length());
            //读写操作
            byte[] buffer = new byte[20];
            int len;
            while ((len = raf1.read(buffer)) != -1) {
                //读取数据，然后保存到 builder 里
                builder.append(new String(buffer, 0, len));
            }
            //调入指针，写入"xyz
            raf1.seek(3);
            raf1.write("xyz".getBytes());

            //将StringBuilder中的数据写入到文件中
            raf1.write(builder.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (raf1 != null) {
                try {
                    raf1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //思考：将StringBuilder替换为ByteArrayOutputStream
    }
}
