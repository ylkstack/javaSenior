package com.atguigu.java1;
import org.junit.Test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * UDPd协议的网络编程
 * @author: ylkstack  Email:158656887@qq.com
 * @Date: 2021/8/8 22:38
 *
 * 若想接收成功，需接收端先启动
 */
public class UDPTest {
    //发送端
    @Test
    public void sender() throws IOException {
        //发送端无需指明端口号
        DatagramSocket socket = new DatagramSocket();

        String string = "我是UDP方式发送的导弹";
        byte[] data = string.getBytes();//String转换为字节数组
        InetAddress inet = InetAddress.getLocalHost();//此处是本地的Ip 如果是其他的 InetAddress.getByName()
        //封装数据报
        DatagramPacket packet = new DatagramPacket(data,0,data.length,inet,9090);
        //发送
        socket.send(packet);
        //关闭
        socket.close();
    }
    //接收端
    @Test
    public void receiver() throws IOException {
        //接收端需要指明端口号（指明的是对方的端口号），不然无法得知水发送的
        DatagramSocket socket = new DatagramSocket(9090);

        //封装接收的数据报
        byte[] buffer = new byte[100];
        DatagramPacket packet = new DatagramPacket(buffer,0,buffer.length);
        //接收数据
        socket.receive(packet);

        //展示控制台,new String方式输出
        System.out.println(new String(packet.getData(),0,packet.getLength()));

        //关闭资源
        socket.close();
    }
}
