package com.fast.family.cache;

import com.google.common.cache.*;
import com.google.common.cache.Cache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class GuavaCacheTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Cache<Integer, Student> studentCache =
                CacheBuilder.newBuilder()
                .concurrencyLevel(8)//并发级别为8，并发级别是指可以同时写缓存的线程数
                .expireAfterWrite(8, TimeUnit.SECONDS)//设置写缓存后8秒钟过期
                .initialCapacity(10)//设置缓存容器的初始容量为10
                .maximumSize(100)//设置缓存最大容量为100，超过100只有就会按照LRU最近最少使用算法来移除缓存
                .recordStats()//设置统计缓存命中率
                .removalListener(new RemovalListener<Integer, Student>() {

                    @Override
                    public void onRemoval(RemovalNotification<Integer, Student> removalNotification) {
                        System.out.println("需要删除的key->" + removalNotification.getKey());
                        System.out.println("需要删除的value->" + removalNotification.getValue());
                    }
                }).build();

        studentCache.put(1,null);

//        for (int i = 0;i < 20;i++){
//            Student student = studentCache.get(i);
//            System.out.println(student.toString());
//            TimeUnit.SECONDS.sleep(1);
//        }
//        System.out.println("Cache stats:");
//        //最后打印缓存命中率
//        System.out.println(studentCache.stats().toString());
    }
}
