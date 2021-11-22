package com.atguigu.exer2;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * 利用File构造器，new 一个文件目录file
 * 在其中创建多个文件和目录
 * 编写方法，实现删除file中指定文件的操作
 * @author: ylkstack  Email:158656887@qq.com
 * @Date: 2021/7/28 21:27
 */
public class FileDemo {
    @Test
    public void test1() throws IOException {
        File file = new File("D:\\io\\io1\\hello.txt");
        //创建一个与file同目录下的另外一个文件，文件名为：haha.txt
        File desFile = new File(file.getParent(),"haha.txt");
        boolean newFile = desFile.createNewFile();//需要抛异常
        if (newFile){
            System.out.println("创建成功！");
        }
        File desFile1 = new File(file.getParent(),"haha\\hi");
        boolean mkdirs = desFile1.mkdirs();
        if (mkdirs){
            System.out.println("目录创建成功");
        }

        //删除一个目录：hi   此方法默认删除最后一个盘符
        if (desFile1.exists()){
            desFile1.delete();
            System.out.println("删除成功！");
        }
    }
}
