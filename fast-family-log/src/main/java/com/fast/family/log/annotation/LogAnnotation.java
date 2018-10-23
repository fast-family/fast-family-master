package com.fast.family.log.annotation;

import java.lang.annotation.*;

/**
 * @author 张顺
 * @version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface LogAnnotation {


    String methodName() default "";

    String methodType() default "";

    String desc() default "";
}
