package com.atguigu.java1;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 实现TCP的网络编程
 * 例题3：从客户端发送文件给服务端，服务端保存到本地。并返回“发送成功”给 客户端。并关闭相应的连接。
 *
 * @author: ylkstack  Email:158656887@qq.com
 * @Date: 2021/8/7 22:04
 */
public class TCPTest3 {
    @Test
    public void client(){
        Socket socket = null;
        OutputStream outputStream = null;
        FileInputStream fis = null;
        InputStream is = null;

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
            //关闭数据的输出
            socket.shutdownOutput();//此处是关闭数据输出，告知服务端已传输完成。防止read阻塞
            //接收来自于服务器端的数据，并显示到控制台
            is = socket.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer1 = new byte[20];
            int len1;
            while ((len1 = is.read(buffer1)) != -1) {
                baos.write(buffer1,0,len1);
            }
            System.out.println(baos.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
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
    public void server() {
        ServerSocket serverSocket = null;
        Socket socket = null;
        InputStream is = null;
        FileOutputStream fileOutputStream = null;
        OutputStream os = null;
        try {
            //创建ServerSocket  同时填入客户端写下的端口号
            serverSocket = new ServerSocket(9090);
            //调用accept()表示接收来自于客户端的socket
            socket = serverSocket.accept();
            //创建输入流
            is = socket.getInputStream();
            //读取输出流中的数据
            //因为需要保存到本地，所以需要创建FileOutputStream
            fileOutputStream = new FileOutputStream(new File("图片2.jpg"));

            //读写操作
            byte[] buffer = new byte[1024];
            int len;
            while ((len = is.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, len);
            }

            System.out.println("图片传输完成");//测试是否执行到此
            //服务器端给与客户端反馈
            os = socket.getOutputStream();
            os.write("你好，美女，照片已收到，非常漂亮".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
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
