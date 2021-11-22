package com.atguigu.java1;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 测试下载文件
 * @author: ylkstack  Email:158656887@qq.com
 * @Date: 2021/8/9 20:06
 */
public class URLTest1 {
    public static void main(String[] args){
        HttpURLConnection urlConnection = null;
        InputStream is = null;
        FileOutputStream fos = null;
        try {
            URL url = new URL("http://localhost:8080/examples/tupian.jpg");

            //获取链接-----使用的协议是Http协议的链接
            urlConnection = (HttpURLConnection) url.openConnection();
            //获取连接
            urlConnection.connect();
            //获取流
            is = urlConnection.getInputStream();
            //获取流之后先读取然后写入到 对应的本地中的文件里
            fos = new FileOutputStream("day10\\图片3.jpg");

            byte[] buffer = new byte[1024];
            int len;
            while ((len = is.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }
            System.out.println("下载完成");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (urlConnection != null) {
                //关闭链接不报异常，不需要try-catch
                    urlConnection.disconnect();//断开连接
            }
        }

    }
}
