package com.atguigu.java1;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * URL网络编程
 * 1.URL:统一资源定位符，对应着互联网的某一个资源地址
 * 2.格式：
 *  http://localhost:8080/examples/图片.jpg?usename=Tom
 *  协议    主机名    端口号    具体资源地址                    参数列表
 * @author: ylkstack  Email:158656887@qq.com
 * @Date: 2021/8/9 0:41
 */
public class URLTest {
    public static void main(String[] args) {
        try {
            URL url = new URL("http://localhost:8080/examples/图片.jpg?usename=Tom");

//            URL类常用方法
//	一个URL对象生成后，其属性是不能被改变的，但可以通过它给定的 方法来获取这些属性
//	public String getProtocol( )   获取该URL的协议名
            System.out.println(url.getProtocol());//http
//	public String getHost( )        获取该URL的主机名
            System.out.println(url.getHost());//localhost
//	public String getPort( )         获取该URL的端口号
            System.out.println(url.getPort());//8080
//	public String getPath( )       获取该URL的文件路径
            System.out.println(url.getPath());///examples/图片.jpg
//	public String getFile( )        获取该URL的文件名
            System.out.println(url.getFile());///examples/图片.jpg?usename=Tom
//	public String getQuery()       获取该URL的查询名
            System.out.println(url.getQuery());//usename=Tom


        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
