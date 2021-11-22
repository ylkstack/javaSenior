package com.atguigu.java;

/**
 * @author: ylkstack  Email:158656887@qq.com
 * @Date: 2021/8/29 20:18
 */
public class MyInterfaceImpl implements MyInterface{
    //实现抽象方法
    @Override
    public void methodAbstrace() {
    }

//    重写默认方法（有方法体的叫重写，无方法体的叫实现）
    @Override
    public void methodDefault() {
        System.out.println("实现类重写了接口中的默认方法");
    }
    public static void main(String[] args) {
        //接口中的静态方法只能由接口自己调用
        MyInterface.methodStatic();
        //接口的实现类不能调用接口的静态方法
//        MyInterfaceImpl.methodStatic();
        MyInterfaceImpl impl = new MyInterfaceImpl();
        //未重写时调用的是接口中的，重写后调用的是实现类重写的方法
        impl.methodDefault();

        //接口的私有方法，不能在接口外部调用
//        impl.methodPrivate();
    }
}
