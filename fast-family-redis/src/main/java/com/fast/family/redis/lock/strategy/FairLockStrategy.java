package com.fast.family.core.redis.lock.strategy;

import com.fast.family.core.redis.lock.entity.LockInfo;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

/**
 * @author 张顺
 * @version 1.0
 */
@Slf4j
public class FairLockStrategy implements LockStrategy {

    private RedissonClient redissonClient;

    public FairLockStrategy(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    @Override
    public RLock tryLock(LockInfo lockInfo) throws InterruptedException {
        RLock lock = redissonClient.getLock(lockInfo.getLockName());
        boolean isAcquiringLock = lock.tryLock(lockInfo.getWaitTime(),lockInfo.getLockTime(), TimeUnit.MILLISECONDS);
        return isAcquiringLock == true ? lock : null;
    }

    @Override
    public void unlock(RLock lock) {
        if (lock != null && lock.isLocked()){
            lock.unlock();
        }
    }
}
