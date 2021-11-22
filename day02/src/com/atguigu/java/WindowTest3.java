package com.atguigu.java;

/**
 * 使用同步方法解决时限Runnable接口的线程安全问题
 *
 * 关于同步方法的总结：
 * 1. 同步方法仍然设计到同步监视器，只是不需要我们显示的声明
 * 2.非静态的同步方法，同步监视器是：this
 *    静态的同步方法，同步监视器是：当前类本身
 *
 *
 * @author: ylkstack  Email:158656887@qq.com
 * @Date: 2021/6/14 21:35
 */
class Window3 implements Runnable{
    private int ticket = 100;

    @Override
    public void run() {
        while(true) {
            show();
        }
    }

    //把 synchronized 声明在方法里。表示同步方法
    private synchronized void show(){//同步方法的同步监视器是：this
        if (ticket > 0) {

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + ":卖票，票号为：" +
                    ticket);
            ticket--;
        }
    }
}
public class WindowTest3 {
    public static void main(String[] args) {
        Window3 window3 = new Window3();

        Thread t1 = new Thread(window3);
        Thread t2 = new Thread(window3);
        Thread t3 = new Thread(window3);

        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");
        t1.start();
        t2.start();
        t3.start();

    }
}