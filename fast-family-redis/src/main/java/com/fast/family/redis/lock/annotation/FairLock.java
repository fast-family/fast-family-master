package com.fast.family.redis.lock.annotation;

import java.lang.annotation.*;

/**
 * @author 张顺
 * @version 1.0
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FairLock {

    //锁名称
    String lockName() default "";

    //持锁时间
    //单位毫秒，默认三十秒
    long lockTime() default 5000l;

    //等待时间
    //单位毫秒，默认三十秒
    long waitTime() default 3000l;

    //是否异步执行
    boolean async() default false;
}
