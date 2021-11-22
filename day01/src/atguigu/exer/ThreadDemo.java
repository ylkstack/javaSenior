package atguigu.exer;

/**
 * 练习：创建两个分线程，其中一个线程遍历100以内的偶数，另一个线程遍历100以内的奇数
 * @author: ylkstack  Email:158656887@qq.com
 * @Date: 2021/6/13 21:17
 */
public class ThreadDemo {
    public static void main(String[] args) {
//        MyThread1 my1 = new MyThread1();
//        MyThread2 my2 = new MyThread2();
//        my1.start();
//        my2.start();

        //创建Thread类的匿名子类的方式
        new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    if (i % 2 == 0)
                        System.out.println(Thread.currentThread() + ":" + i);
                }
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    if (i % 2 !=0)
                        System.out.println(i);
                }
            }
        }.start();
    }
}

class MyThread1 extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
           if (i % 2 == 0)
            System.out.println(Thread.currentThread() + ":" + i);
        }
    }
}

class MyThread2 extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 !=0)
                System.out.println(i);
        }
    }
}
