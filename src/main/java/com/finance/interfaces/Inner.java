package com.finance.interfaces;

import java.lang.annotation.*;

/**
 * 注解在controller方法上，当value为false时，该接口不进行登陆验证
 * @author : shenhao
 * @date : 2019/11/5 10:30
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Inner {

    boolean value() default true;

}
