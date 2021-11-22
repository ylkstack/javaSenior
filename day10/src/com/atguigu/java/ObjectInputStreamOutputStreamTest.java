package com.atguigu.java;


import org.junit.Test;

import java.io.*;

/**
 * 对象流的使用
 * 1.ObjectInputStream和OjbectOutputSteam
 * 2.作用：用于存储和读取基本数据类型数据或对象的处理流。它的强大之处就是可 以把Java中的对象写入到数据源中，也能把对象从数据源中还原回来。
 *
 * 3.要想一个java对象是可系列化的，要满足相应的要求。见：Person
 *
 * 补充：ObjectOutputStream和ObjectInputStream不能序列化static和transient修
 * 饰的成员变量
 * @author: ylkstack  Email:158656887@qq.com
 * @Date: 2021/8/2 11:45
 */
public class ObjectInputStreamOutputStreamTest {

    /*
 序列化的过程：将内存中的java对象保存到磁盘中。或通过网络传输出去
 使用ObjectOutputStream实现
  */
    @Test
    public void testObjectOutputStream(){
        ObjectOutputStream oos = null;
        try {
            //1.创建序列化对象流
            oos = new ObjectOutputStream(new FileOutputStream("object.dat"));
            //2.写入数据
            oos.writeObject("我爱北京天安门");

            oos.flush();//刷新操作

            oos.writeObject(new Person("王铭",23));
            oos.flush();//一定要刷新哦

            oos.writeObject(new Person("王铭",23,1001,new Account(5000)));
            oos.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                //3.关闭资源
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /*
    反序列化:将磁盘文件中的对象还原为内存中的一个java对象
    使用ObjectInputStream来实现
     */
    @Test
    public void testObjectInputStream(){
        ObjectInputStream ois = null;
        try {
            //创建反序列号对象流
            ois = new ObjectInputStream(new FileInputStream("object.dat"));

            Object obj = ois.readObject();
            String str = (String) obj;

            Person p = (Person) ois.readObject();
            Person p1 = (Person) ois.readObject();

            System.out.println(str);
            System.out.println(p);
            System.out.println(p1);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
