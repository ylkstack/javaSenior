package com.atguigu.java4;

import org.junit.Test;

import java.util.Optional;

/**
 * Optional类：为了在程序在避免出现空指针异常而创建的
 *
 * 常用的方法：ofNullable(T t)
 *
 * @author: ylkstack  Email:158656887@qq.com
 * @Date: 2021/8/25 0:37
 */
public class OptionalTest {
    /*
    创建Optional类对象的方法：
Optional.of(T t) : 创建一个 Optional 实例，t必须非空；
Optional.empty() : 创建一个空的 Optional 实例
Optional.ofNullable(T t)：t可以为null

     */
    @Test
    public void test1() {
        Girl girl = new Girl();
//        girl = null;
        //of(T t):保证t是非空的
        Optional<Girl> optionalGirl = Optional.of(girl);
        System.out.println(optionalGirl);
    }
    @Test
    public void test2() {
        Girl girl = new Girl();
//        girl = null;
        //ofNullable(T t):t 可以为null
        //如果非空结果为下面的
        Optional<Object> optionalGirl = Optional.ofNullable(girl);
        System.out.println(optionalGirl);

        //orElse(T t):如果当前的Optional内部封装的t是非空的，则返回内部的t
        //如果内部的t是空的，则返回orElse(T t1)方法中的参数t1
        //如果为空，结果为下面的
        Object girl1 = optionalGirl.orElse(new Girl("赵丽颖"));
        System.out.println(girl1);
    }

    public String getGirlName(Boy boy) {
        return boy.getGirl().getName();//获取女孩的名字
    }

    @Test
    public void test3() {
        Boy boy = new Boy();//Boy()未赋值，会空指针
        boy = null;//boy本身也有可能是null，也会报空指针
        String girlName = getGirlName(boy);
        System.out.println(girlName);
    }

    //优化以后的getGirlName():
    public String getGirlName1(Boy boy) {
        if (boy != null) {
            Girl girl = boy.getGirl();
            if (girl != null) {
                return girl.getName();
            }
        }
        return null;
    }

    //优化后测试
    @Test
    public void test4() {
        Boy boy = new Boy();//Boy()未赋值，会空指针
        boy = null;//boy本身也有可能是null，也会报空指针
        String girlName = getGirlName1(boy);
        System.out.println(girlName);
    }

    //使用Optional类的getGirlName()
    public String getGirlName2(Boy boy) {
        Optional<Boy> boyOptional = Optional.ofNullable(boy);
        //此时的boy1一定非空
        Boy boy1 = boyOptional.orElse(new Boy(new Girl("迪丽热巴")));
        Girl girl = boy1.getGirl();
        Optional<Girl> girl1Optional = Optional.ofNullable(girl);
        //此时的girl1Optional一定非空
        Girl girl1 = girl1Optional.orElse(new Girl("古力娜扎"));
        return girl1.getName();
    }

    @Test
    public void test5() {
        Boy boy = null;
        boy = new Boy();//此处未为Boy()赋值，Girl为空
        boy = new Boy(new Girl("苍老师"));//如果都不为空，结果为此项
        String girlName = getGirlName2(boy);
        System.out.println(girlName);//结果:迪丽热巴   //当boy为非空时，结果为:古力娜扎
    }
}
