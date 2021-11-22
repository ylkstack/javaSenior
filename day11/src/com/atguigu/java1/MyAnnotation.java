package com.atguigu.java1;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * @author: ylkstack  Email:158656887@qq.com
 * @Date: 2021/8/14 21:45
 */
@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE, MODULE})
@Retention(RetentionPolicy.RUNTIME)//注解开发中一般使用RUNTIME
public @interface MyAnnotation {
    String value() default "hello";//default "hello"  声明的默认值
}
