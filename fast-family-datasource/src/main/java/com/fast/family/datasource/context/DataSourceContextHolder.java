package com.fast.family.datasource.context;

/**
 * @author 张顺
 * @version 1.0
 */
public class DataSourceContextHolder {


    private static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<>();


    public static void add(String name) {
        THREAD_LOCAL.set(name);
    }

    public static String get() {
        return THREAD_LOCAL.get();
    }

    public static void clear() {
        THREAD_LOCAL.remove();
    }
}
