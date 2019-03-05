package com.fast.family.core.redis.lock.entity;

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

    private boolean fair;


    public LockInfo converterLock(ReentrantLock lock){
        return createLockInfo(lock.lockName(),lock.lockTime(),lock.waitTime()
                ,lock.async(),lock.fair());
    }

    public LockInfo converterReadLock(ReadLock readLock){
        return createLockInfo(readLock.lockName(),readLock.lockTime(),readLock.waitTime()
                ,readLock.async(),readLock.fair());
    }

    public LockInfo converterWriteLock(WriteLock writeLock){
        return createLockInfo(writeLock.lockName(),writeLock.lockTime(),writeLock.waitTime()
                ,writeLock.async(),writeLock.fair());
    }

    private LockInfo createLockInfo(String lockName,long lockTime,long waitTime
                        ,boolean async,boolean fair){
        this.setAsync(async);
        this.setFair(fair);
        this.setLockName(lockName);
        this.setLockTime(lockTime);
        this.setWaitTime(waitTime);
        return this;
    }

}
