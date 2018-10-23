package com.fast.family.datasource.context;

/**
 * @author 张顺
 * @version 1.0
 */
public class DataSourceContextHolder {


    private static final ThreadLocal<String> threadLocal = new ThreadLocal<>();


    public static void add(String name){
        threadLocal.set(name);
    }

    public static String get(){
        return threadLocal.get();
    }

    public static void clear(){
        threadLocal.remove();
    }
}
