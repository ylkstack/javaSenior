package com.atguigu.java;

import java.util.ArrayList;
import java.util.List;

/**
 * 子类继承带泛型的父类时，子类如若未特意指明泛型就是普通类，不是泛型类
 * @author: ylkstack  Email:158656887@qq.com
 * @Date: 2021/7/25 16:12
 */
public class SubOrder extends Order<Integer>{

    public static <E> List<E> copyFromArrayToList(E[] arr){

        ArrayList<E> list = new ArrayList<>();
        for (E e :
                arr) {
            list.add(e);
        }
        return list;
    }
}
