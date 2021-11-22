package com.atguigu.java;

import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;

/**
 * @author: ylkstack  Email:158656887@qq.com
 * @Date: 2021/8/4 21:43
 */
public class FileUtilsTest {
    public static void main(String[] args) {
        File srcFile = new File("day10\\图片.jpg");
        File destFile = new File("day10\\图片2.jpg");
        try {
            FileUtils.copyFile(srcFile,destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
