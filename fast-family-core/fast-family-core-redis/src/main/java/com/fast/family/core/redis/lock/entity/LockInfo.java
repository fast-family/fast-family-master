package com.fast.family.core.redis.lock.entity;

import com.fast.family.core.redis.lock.annotation.FairLock;
import com.fast.family.core.redis.lock.annotation.ReentrantLock;
import com.fast.family.core.redis.lock.annotation.ReadLock;
import com.fast.family.core.redis.lock.annotation.WriteLock;
import lombok.Data;

/**
 * @author 张顺
 * @version 1.0
 */
@Data
public class LockInfo {

    private String lockName;

    private long lockTime;

    private long waitTime;

    private boolean async;


    public LockInfo converterFairLock(FairLock lock){
        return createLockInfo(lock.lockName(),lock.lockTime(),lock.waitTime()
                ,lock.async());
    }

    public LockInfo converterReentrantLock(ReentrantLock lock){
        return createLockInfo(lock.lockName(),lock.lockTime(),lock.waitTime()
                ,lock.async());
    }

    public LockInfo converterReadLock(ReadLock lock){
        return createLockInfo(lock.lockName(),lock.lockTime(),lock.waitTime()
                ,lock.async());
    }

    public LockInfo converterWriteLock(WriteLock lock){
        return createLockInfo(lock.lockName(),lock.lockTime(),lock.waitTime()
                ,lock.async());
    }

    private LockInfo createLockInfo(String lockName,long lockTime,long waitTime
                        ,boolean async){
        this.setAsync(async);
        this.setLockName(lockName);
        this.setLockTime(lockTime);
        this.setWaitTime(waitTime);
        return this;
    }

}
