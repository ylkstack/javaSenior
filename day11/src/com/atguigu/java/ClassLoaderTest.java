package com.atguigu.java;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 了解类的加载器
 * @author: ylkstack  Email:158656887@qq.com
 * @Date: 2021/8/13 22:41
 */
public class ClassLoaderTest {
    @Test
    public void test1() {
        //对于自定义类，使用系统类加载器进行加载
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        System.out.println(classLoader);//结果：AppClassLoader
        //调用系统类加载器的getParent():获取扩展类加载器
        ClassLoader classLoader1 = classLoader.getParent();
        System.out.println(classLoader1);//结果：PlatformClassLoader
        //调用扩展类加载器的getParent():无法获取引导类加载器
        //引导类加载器主要负责加载java的核心类库，无法加载自定义类的
        ClassLoader classLoader2 = classLoader1.getParent();
        System.out.println(classLoader2);//null

        //String 系统类库的类是使用引导类加载器加载的，如法获取，结果为Null
        ClassLoader classLoader3 = String.class.getClassLoader();
        System.out.println(classLoader3);
    }

    /*
    PropertiesL用来读取配置文件。（掌握）

     */
    @Test
    public void test2() throws IOException {
        //创建Properties文件
        Properties pros = new Properties();
        //创建文件流
        //读取配置文件方式一：
//        FileInputStream fis = new FileInputStream("src\\jdbc1.properties");
//        pros.load(fis);
        //读取配置文件方式二：使用ClassLoder
        //创建类加载器
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        //getResourceStream():以流的方式创建一个资源
        InputStream resourceAsStream = classLoader.getResourceAsStream("jdbc1.properties");
        pros.load(resourceAsStream);

        //读取配置信息
        String user = pros.getProperty("user");
        String password = pros.getProperty("password");
        System.out.println("user = " + user + ",password = " + password);

    }
}
