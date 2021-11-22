package com.atguigu.java1;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 实现TCP的网络编程
 * 例题：客户端发送文件给服务端，服务端将文件保存在本地。
 * @author: ylkstack  Email:158656887@qq.com
 * @Date: 2021/8/6 23:24
 */
public class TCPTest2 {
    @Test
    public void client(){
        Socket socket = null;
        OutputStream outputStream = null;
        FileInputStream fis = null;
        try {
            //创建Socket并赋予对方的IP地址和端口号
            socket = new Socket(InetAddress.getByName("192.168.0.2"),9090);

            //创建输出流
            outputStream = socket.getOutputStream();

            //创建输入流
            fis = new FileInputStream(new File("图片.jpg"));

            //写入写出操作
            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer)) != -1) {
                //写出操作
                outputStream.write(buffer,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Test
    public void server(){
        ServerSocket serverSocket = null;
        Socket socket = null;
        InputStream is = null;
        FileOutputStream fileOutputStream = null;
        try {
            //创建ServerSocket  同时填入客户端写下的端口号
            serverSocket = new ServerSocket(9090);
            //调用accept()表示接收来自于客户端的socket
            socket = serverSocket.accept();
            //创建输入流
            is = socket.getInputStream();
            //读取输出流中的数据
            //因为需要保存到本地，所以需要创建FileOutputStream
            fileOutputStream = new FileOutputStream(new File("图片1.jpg"));

            //读写操作
            byte[] buffer = new byte[1024];
            int len;
            while ((len = is.read(buffer)) != -1) {
                fileOutputStream.write(buffer,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (serverSocket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
