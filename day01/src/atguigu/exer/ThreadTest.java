package atguigu.exer;

/**
 * 代码完成实现Runnable接口的方法创建分线程，并遍历100以内的自然数
 * @author: ylkstack  Email:158656887@qq.com
 * @Date: 2021/6/14 15:47
 */
public class ThreadTest{
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        Thread t1 = new Thread(myThread);
        t1.setName("线程一");
        t1.start();
    }
}

class MyThread implements Runnable{
    public void run(){
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + ":" + i);
        }
    }
}
