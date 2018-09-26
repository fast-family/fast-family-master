package com.fast.family.utils;


public class ClassUtils {

    public static String getShortName(Class<?> clazz){
        return org.springframework.util.ClassUtils.getShortName(clazz);
    }

    public static Class<?> forName(String name) throws ClassNotFoundException {
        return org.springframework.util.ClassUtils.forName(name,null);
    }
}
