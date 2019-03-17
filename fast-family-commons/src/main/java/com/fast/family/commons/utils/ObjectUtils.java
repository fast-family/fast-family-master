package com.fast.family.commons.utils;

/**
 * @author 张顺
 * @version 1.0
 */
public class ObjectUtils {


    public static boolean isEmpty(Object o){
        return org.springframework.util.ObjectUtils.isEmpty(o);
    }

    public static boolean isNotEmpty(Object o){
        return !isEmpty(o);
    }
}
