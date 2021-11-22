package com.atguigu.exer;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: ylkstack  Email:158656887@qq.com
 * @Date: 2021/7/16 20:54
 */
public class ListExer {
    /*
    区分List中remove(int index)和remove(Object obj)
     */
    @Test
    public void testListRemove() {
        List list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        updateList(list);
        System.out.println(list);//
    }

    private static void updateList(List list) {
//        list.remove(2);//这里是按索引删除的
        list.remove(new Integer(2));//此处删除的是数据：2

    }


}
