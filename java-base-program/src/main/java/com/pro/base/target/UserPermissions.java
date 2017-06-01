package com.pro.base.target;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by zhanghaichao on 2017/5/18.
 * 自定义注解 判断用户权限
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UserPermissions {
    String value() default "blacklist"; //用户权限代码
}
