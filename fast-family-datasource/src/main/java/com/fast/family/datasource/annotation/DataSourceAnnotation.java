package com.fast.family.datasource.annotation;

import java.lang.annotation.*;

/**
 * @author 张顺
 * @version 1.0
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSourceAnnotation {


    String name() default "";
}
