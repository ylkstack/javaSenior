package com.atguigu.java;

/**
 * 静态代理举例
 *
 * 特点：代理类和被代理类在编译期间，就确定下来了
 * @author: ylkstack  Email:158656887@qq.com
 * @Date: 2021/8/17 23:30
 */
interface ClothFactory{//服装厂
    void produceCloth();//生产衣服
}

//代理类
class ProxyClothFactory implements ClothFactory {
    private ClothFactory factory;//用被代理类对象进行实例化

    public ProxyClothFactory(ClothFactory factory) {
        this.factory = factory;
    }

    @Override
    public void produceCloth() {
        System.out.println("代理工厂做一些准备工作");
        factory.produceCloth();

        System.out.println("代理工厂做一些后续的收尾工作");
    }
}

//被代理类
class NikeClothFactory implements ClothFactory{

    @Override
    public void produceCloth() {
        System.out.println("Nike工厂生产一批运动服");
    }
}
public class StaticProxyTest {
    public static void main(String[] args) {
        //创建被代理类的对象
        NikeClothFactory nike = new NikeClothFactory();
        //创建代理类的对象
        ProxyClothFactory proxyClothFactory = new ProxyClothFactory(nike);

        proxyClothFactory.produceCloth();
    }
}
