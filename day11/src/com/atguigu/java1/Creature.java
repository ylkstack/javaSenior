package com.atguigu.java1;

import java.io.Serializable;

/**
 * @author: ylkstack  Email:158656887@qq.com
 * @Date: 2021/8/14 21:33
 */
public class Creature<T> implements Serializable {
    private char gender;//性别
    public double weight;//体重

    private void breath() {
        System.out.println("生物呼吸");
    }

    public void eat() {
        System.out.println("生物吃东西");
    }
}
