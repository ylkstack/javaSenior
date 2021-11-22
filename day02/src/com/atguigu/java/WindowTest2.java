package com.atguigu.java;

/**
 * @author: ylkstack  Email:158656887@qq.com
 * @Date: 2021/6/14 21:11
 */

/*
 * 使用同步代码块解决继承Thread类的方式的线程安全问题
 *
 * 例子：创建三个窗口卖票。总票数为100张，使用继承Thread类的方式
 *
 * 说明：在实现Runnable接口创建多线程的方式中，慎用this充当同步监控器，
 * 考虑使用当前类充当同步监视器
 *
 */
class Window2 extends Thread{
    private static int ticket = 100;//ticket票的意思
    private static Object object = new Object();
    @Override
    public void run() {

            while (true) {
                //继承类的多线程，不能使用 this 充当同步锁。因为需要创建多个对象  this就代表多个对象
//        synchronized(object) {
                synchronized(Window2.class){//此处Window2.class 也为对象，因为类本身也是对象
                    //因为类只会加载一次，所有Window2.class 也是可以充当锁的
                if (ticket > 0) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + ":卖票、票号为" +
                            ticket);
                    ticket--;
                } else {
                    break;
                }
            }
        }
    }
}
public class WindowTest2 {
    public static void main(String[] args) {
        Window2 t1 = new Window2();
        Window2 t2 = new Window2();
        Window2 t3 = new Window2();
        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();
    }
}
