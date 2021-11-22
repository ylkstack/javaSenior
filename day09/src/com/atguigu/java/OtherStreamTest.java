package com.atguigu.java;

import org.junit.Test;

import java.io.*;

/**
 * 其他流的使用
 * 1.标准的输入、输出流
 * 2.打印流
 * 3.数据流
 *
 * @author: ylkstack  Email:158656887@qq.com
 * @Date: 2021/8/1 20:59
 */
public class OtherStreamTest {
    /*
    1.标准的输入、输出流
    System.in:标准的输入流，默认从键盘输入
    System.out：标准的输出流，默认从控制台输出
    1.2
    System类的setIn(InputStream is) / setOut(PrintStream ps)方式重新指定输入和输出的流

    1.3练习：
    从键盘输入字符串，要求将读取到的整行字符串转成大写输出。然后继续
    进行输入操作，直至当输入“e”或者“exit”时，退出程序。

        方式一：使用Scanner实现，调用next()返回一个字符串
        方式二：使用System.in实现。System.in ---> 转换流---> BufferedReader的readLine()
     */
    public static void main(String[] args) {
        BufferedReader br = null;
        try {
            //创建转换流（输入流）
            InputStreamReader isr = new InputStreamReader(System.in);
            //创建缓冲流
            br = new BufferedReader(isr);

            while (true) {
                System.out.println("请输入字符串:");
                String data = br.readLine();//读入一行数据、
                //data写在后面，能更好的避免空指针问题
                if ("e".equalsIgnoreCase(data) || "exit".equalsIgnoreCase(data)) {
                    System.out.println("程序结束");
                    break;
                }

                String upperCase = data.toUpperCase();
                System.out.println(upperCase);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /*
    2.打印流：PrintStream 和PrintWriter
    2.1 提供了一系列重载的print()和println()
    2.2 练习：
     */

    @Test
    public void test1(){
        PrintStream ps = null;
        try {
            FileOutputStream fos = new FileOutputStream(new File("D:\\IO\\text.txt"));
    // 创建打印输出流,设置为自动刷新模式(写入换行符或字节 '\n' 时都会刷新输出缓冲区)
          ps = new PrintStream(fos, true);
            if (ps != null) {// 把标准输出流(控制台输出)改成文件
                System.setOut(ps);//此处set输出方式，输出到ps--for里的D:\IO\text.txt里
            }
            for (int i = 0; i <= 255; i++) { // 输出ASCII字符
                System.out.print((char) i);
                if (i % 50 == 0) { // 每50个数据一行
                    System.out.println(); // 换 行
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
        }

    }

    /*
    3.数据流
    3.1 DataInputStream 和 DataOutputStream
    3.2 作用：用于读取或写出基本数据类型的变量和字符串

      练习：将内存中的字符串、基本数据类型的变量写出到文件中

      注意：处理异常的话，仍然应该使用try-catch-finally
     */
    @Test
    public void test3(){

        //将内存中的字符串、基本数据类型的变量写出到文件中
        //注意：读取不同类型的数据的顺序要与当初写入文件时，保存的数据的顺序一致
        DataOutputStream dos = null;
        try {
            //创建一个输出流
            dos = new DataOutputStream(new FileOutputStream("data.txt"));
            //写入数据到 data.txt 文件中
            dos.writeUTF("刘建晨");
            dos.flush();//显式的刷新到文件当中，类似于保存。一旦执行操作，把数据写入的到文件里
            dos.writeInt(23);
            dos.flush();
            dos.writeBoolean(true);
            dos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (dos != null)
                try {
                    //关闭资源
                    dos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    /*
    将文件中存储的基本数据类型变量和字符串读取到内存中，保存在变量中
     */
    @Test
    public void test4(){
        DataInputStream dis = null;
        try {
            //创建一个输入流，供读取信息  (匿名节点输入流)
            dis = new DataInputStream(new FileInputStream("data.txt"));
            //读取：按写入顺序读取
            String name = dis.readUTF();
            int age = dis.readInt();
            boolean isMale = dis.readBoolean();

            System.out.println("name = " + name);
            System.out.println("age = " + age);
            System.out.println("isMale = " + isMale);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (dis != null)
                //关闭资源（流）
                try {
                    dis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }


    }
}
