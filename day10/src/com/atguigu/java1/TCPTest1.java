package com.atguigu.java1;

import org.junit.Test;


import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 实现TCP的网络编程
 * 例子1：客户端发送信息给服务端，服务端将数据显示在控制台上
 * @author: ylkstack  Email:158656887@qq.com
 * @Date: 2021/8/6 0:02
 */
public class TCPTest1 {
    //客户端
    @Test
    public void client(){
        Socket socket = null;//88899为端口号
        OutputStream os = null;
        try {
            //1.创建Socket对象，指明服务器端的ip和端口号
            InetAddress inet = InetAddress.getByName("192.168.0.2");
            socket = new Socket(inet,8899);
            //2.获取一个输出流。用于输出数据
            os = socket.getOutputStream();
            //3.写出数据的操作
            os.write("你好，我是客户端mm".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //资源的关闭
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null) {
                try {
                    socket.close();//socket也是一个资源，调用完需要进行关闭
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    //服务端
    @Test
    public void server(){

        ServerSocket ss = null;
        Socket socket = null;
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        try {
            //1.创建服务器端的ServerSocket，指明自己的端口号（服务器在哪台跑，就是那台IP所以不需要指定IP）
            ss = new ServerSocket(8899);
            //2.调用accept()表示接收来自于客户端的socket
            socket = ss.accept();
            //获取输入数据
            is = socket.getInputStream();

            //不建议这样写，可能会有乱码
//        byte[] buffer = new byte[5];
//        int len;
//        while ((len = is.read(buffer)) != -1){
//            String str = new String(buffer,0,len);
//            System.out.println(str);
//        }
            //4.读取输出流中的数据
            baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[5];
            int len;
            while ((len = is.read(buffer)) != -1) {
                baos.write(buffer,0,len);
            }

            System.out.println(baos.toString());//此处显式声明toString() 防止乱码
            //查看谁发送的信息的操作
            System.out.println("收到了来自于：" + socket.getInetAddress().getHostAddress() + "数据");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //5.关闭资源
            if (baos != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    ss.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (ss != null) {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
