package atguigu.java;

/**
 * 测试Thread中的常用方法：
 * 1.start():启动当前线程：调用当前线程的run()
 * 2.run():通常需要重写Thread类中的方法，将创建的线程要执行的操作声明在此方法中
 * 3.currentThread:静态方法，返回执行当前代码的线程
 * 4.getName():获取当前线程的名字
 * 5.setName():设置当前线程的名字     也可以通过构造器为线程命名
 * 6.yield（）：释放当前cpu的执行，让步线程，有可能还持续抢到本次线程权限
 * 7.join() ：在线程 a 中调用线程 b 的join(),此时线程 a 就进入阻塞状态。
 *            直到线程b完全执行以后。线程a才结束阻塞状态
 * 8.stop(): 已过时，当执行此方法时强制结束当前线程
 * 9.sleep(long millitime)：让当前线程"睡眠“指定的millitime毫秒，
 * 在指定的millitime毫秒时间内，当前线程是阻塞状态
 * 10.boolean isAlive()：返回boolean，判断线程是否还活着
 *
 * 线程的优先级等级
 * 1.
 * MAX_PRIORITY：10  -->最大优先级
 * MIN _PRIORITY：1  -->最小优先级
 * NORM_PRIORITY：5  -->默认优先级
 * 2.如何获取和设置当前线程的优先级
 *      getPriority():获取线程的优先级
 *      setPriority():设置线程的优先级
 *
 *    说明：高优先级的线程要抢占低优先级线程cpu的执行权，但是只能从概率上讲，
 *    高优先级的线程高概率的情况下被执行。并不意味着只有当高优先级的线程执行完成以后，低优先级才执行
 *
 * @author: ylkstack  Email:158656887@qq.com
 * @Date: 2021/6/13 21:35
 */
class HelloThread extends Thread{

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                try {
                    sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //Thread.currentThread().可以取消，因为本身调用他的就是Thread.currentThread().
                System.out.println(Thread.currentThread() + ":" +
                        Thread.currentThread().getPriority() + ":" + i);
            }
            if (i % 20 == 0)
                yield();//此处this调用的当前线程，即当前对象，可以省略
        }
    }

    //通过构造器给线程命名
    public HelloThread(String name){
        super(name);
    }
}
public class ThreadMethodTest {
    public static void main(String[] args) {
        HelloThread h1 = new HelloThread("Thread: 1");

//        h1.setName("线程1");
        //设置分线程的优先级
        h1.setPriority(Thread.MAX_PRIORITY);
        h1.start();

        //给主线程命名
        Thread.currentThread().setName("主线程");
        //设置主线程优先级
        Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0)
                System.out.println(Thread.currentThread() + ":" +
                        Thread.currentThread().getPriority() + ":" + i);

//            if (i == 20) {
//                try {
//                    h1.join();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
        }
//        System.out.println(h1.isAlive());
    }
}
