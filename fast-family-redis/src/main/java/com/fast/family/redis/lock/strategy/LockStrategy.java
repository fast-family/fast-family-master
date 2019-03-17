package com.fast.family.redis.lock.strategy;

import com.fast.family.redis.lock.entity.LockInfo;
import org.redisson.api.RLock;

/**
 * @author 张顺
 * @version 1.0
 */
public interface LockStrategy {

    RLock tryLock(LockInfo lockInfo) throws InterruptedException;

    void unlock(RLock lock);

}
