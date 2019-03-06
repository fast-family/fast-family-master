package com.fast.family.core.redis;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

public class RedissonLockTest {


    private static final String LOCK_NAME = "redisLockName";

    private static final long LOCK_TIME = 5;

    private static final long WAIT_TIME = 6;

    public static void main(String[] args) throws InterruptedException {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.4.127:6379");
        RedissonClient redissonClient = Redisson.create(config);
        RLock rLock = redissonClient.getLock(LOCK_NAME);




//        new Thread(() -> {
//            boolean getLock = false;
//            try {
//                getLock = rLock.tryLock(WAIT_TIME,LOCK_TIME, TimeUnit.SECONDS);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("获取锁是否成功1" + getLock);
//        }).start();
//
//        new Thread(() -> {
//            boolean getLock = false;
//            try {
//                getLock = rLock.tryLock(WAIT_TIME,LOCK_TIME, TimeUnit.SECONDS);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("获取锁是否成功2" + getLock);
//        }).start();
//        redissonClient.shutdown();

    }

}
