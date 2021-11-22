package com.atguigu.java;

/**
 * 使用同步方法处理继承Thread类的方法中的线程安全问题
 *
 *  关于同步方法的总结
 *
 * @author: ylkstack  Email:158656887@qq.com
 * @Date: 2021/6/14 21:45
 */
class Window4 extends Thread{
    private static int ticket = 100;//ticket票的意思
    @Override
    public void run() {

        while (true) {
            show();
            }
        }

        private static synchronized void show(){
//            private synchronized void show(){//此种解决方法是错误的
            if (ticket > 0) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ":卖票、票号为" +
                        ticket);
                ticket--;
            }
        }
    }
public class WindowTest4 {
    public static void main(String[] args) {
        Window4 t1 = new Window4();
        Window4 t2 = new Window4();
        Window4 t3 = new Window4();
        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();
    }
}