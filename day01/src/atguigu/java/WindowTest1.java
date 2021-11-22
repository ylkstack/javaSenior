package atguigu.java;

/**
 * 用实现的方式来体现卖票的多线程
 *
 * 例子：创建三个窗口卖票：总票数为100张.使用继承Thread类的方式
 * 存在线程的安全问题，待解决
 *
 * @author: ylkstack  Email:158656887@qq.com
 * @Date: 2021/6/14 15:12
 */
class Window1 implements Runnable{
    private int ticket = 100;
    @Override
    public void run() {
        while (true){
            if(ticket > 0){
                System.out.println(Thread.currentThread().getName() +
                        ":卖票、票号为：" + ticket);
                ticket--;
            }else {
                break;
            }
        }
    }
}
public class WindowTest1 {
    public static void main(String[] args) {
        Window1 w1 = new Window1();

        Thread t1 = new Thread(w1);
        Thread t2 = new Thread(w1);
        Thread t3 = new Thread(w1);

        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");
        t1.start();
        t2.start();
        t3.start();






    }
}
