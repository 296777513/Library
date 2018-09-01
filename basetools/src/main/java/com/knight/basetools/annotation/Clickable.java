package com.knight.basetools.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * double click check
 *
 * @author liyachao
 * @date 2018/8/15
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Clickable {
    int value() default 1000;

    boolean isNeedToast() default false;
}
