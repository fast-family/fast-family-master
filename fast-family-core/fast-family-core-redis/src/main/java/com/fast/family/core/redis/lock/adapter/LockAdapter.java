package com.fast.family.core.redis.lock.adapter;

import com.fast.family.core.redis.lock.entity.LockInfo;
import org.redisson.api.RLock;

/**
 * @author 张顺
 * @version 1.0
 */
public interface LockAdapter {

    RLock tryLock(LockInfo lockInfo);

    void unlock(RLock lock);

}
