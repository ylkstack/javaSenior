package com.atguigu.java1;

/**
 * 使用同步机制将单例模式中的懒汉式改写为线程安全的
 *
 * @author: ylkstack  Email:158656887@qq.com
 * @Date: 2021/6/15 23:07
 */
public class BankTest {

}

class Bank {
    private Bank() {
    }

    private static Bank instance = null;

    public static Bank getInstance() {//如果写synchronized。此时synchronized的锁为Bank.class本身
        //此时的instance 算是共享数据，多线程操作时存在线程安全隐患
        //方式一：效率稍差
//        synchronized (Bank.class) {
//            if (instance == null){
//                instance = new Bank();
//            }
//            return instance;
//        }

        //方式二：加if判断线程是否进入同步方法     效率稍高，
        if (instance == null) {
            synchronized (Bank.class) {
                if (instance == null) {
                    instance = new Bank();
                }

            }
        }
        return instance;
    }
}
