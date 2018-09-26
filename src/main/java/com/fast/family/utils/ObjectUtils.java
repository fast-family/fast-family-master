package com.fast.family.utils;


public class ObjectUtils {

    public static boolean isEmpty(Object o){
        return org.springframework.util.ObjectUtils.isEmpty(o);
    }

    public static boolean isNotEmpty(Object o){
        return !isEmpty(o);
    }
}
