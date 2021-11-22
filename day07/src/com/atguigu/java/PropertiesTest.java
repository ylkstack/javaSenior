package com.atguigu.java;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * @author: ylkstack  Email:158656887@qq.com
 * @Date: 2021/7/22 22:09
 */
public class PropertiesTest {
    //Properties:常用来处理配置文件，key和value都是String类型
    public static void main(String[] args) throws Exception {
        FileInputStream fis = null;
        String name = null;
        String password = null;
        try {
            Properties properties = new Properties();
            fis = new FileInputStream("jdbc.properties");
            properties.load(fis);//加载流对应的文件

            //取配置文件里的用户名和密码。。。如果写错会找不到相应的值
            name = properties.getProperty("name");
            password = properties.getProperty("password");
            System.out.println("name = " + name + ",password = " + password);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                fis.close();
            }
        }

    }
}
