package com.fast.family.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;


public class ThreadLocalUtils {

    private static final ThreadLocal<Map<String,Object>> LOCAL = ThreadLocal.withInitial(HashMap::new);

    /**
     * 获取ThreadLocal所有值
     * @return
     */
    public static Map<String,Object> getAll(){
        return LOCAL.get();
    }

    /**
     * 存入一个值到ThreadLocal中
     * @param key 键
     * @param value 值
     * @param <T> 值得类型
     * @return
     */
    public static <T>T put(String key,T value){
        LOCAL.get().put(key,value);
        return value;
    }

    /**
     * 删除key对应的值
     * @param key 键
     */
    public static void remove(String key){
        LOCAL.get().remove(key);
    }

    /**
     * 清空ThreadLocal
     */
    public static void clear(){
        LOCAL.remove();
    }

    /**
     * 获取ThreadLocal中的值
     * @param key 键
     * @param <T> 值泛型
     * @return
     */
    public static <T>T get(String key){
        return ((T) LOCAL.get().get(key));
    }

    /**
     * 从ThreadLocal中获取值,并指定一个当值不存在的提供者
     * @param key 键
     * @param supplier 提供者
     * @param <T> 值泛型
     * @return
     */
    public static <T>T get(String key, Supplier<T> supplier){
        return ((T) LOCAL.get().computeIfAbsent(key, k -> supplier.get()));
    }

}
