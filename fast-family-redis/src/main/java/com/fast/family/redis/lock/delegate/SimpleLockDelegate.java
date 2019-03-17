package com.fast.family.redis.lock.delegate;

import com.fast.family.redis.lock.annotation.FairLock;
import com.fast.family.redis.lock.annotation.ReadLock;
import com.fast.family.redis.lock.annotation.ReentrantLock;
import com.fast.family.redis.lock.annotation.WriteLock;
import com.fast.family.redis.lock.entity.LockInfo;
import com.fast.family.redis.lock.strategy.*;
import io.vavr.API;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInvocation;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.lang.reflect.Method;

import static io.vavr.API.$;
import static io.vavr.API.Case;

/**
 * @author 张顺
 * @version 1.0
 */
@Slf4j
public class SimpleLockDelegate implements LockDelegate{


    private LockStrategy lockStrategy;

    private RedissonClient redissonClient;

    public SimpleLockDelegate(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Method method = invocation.getMethod();
        lockStrategy = matchLockStrategy(method);
        LockInfo lockInfo = new LockInfo();
        RLock rLock = null;
        try {
            rLock = lockStrategy.tryLock(lockInfo);
            return invocation.proceed();
        } catch (Exception e) {
            throw e;
        } finally {
            lockStrategy.unlock(rLock);
        }
    }

    private LockStrategy matchLockStrategy(Method method){
        return API.Match(method).of(
                Case($(v -> method.getAnnotation(FairLock.class) != null),new FairLockStrategy(redissonClient)),
                Case($(v -> method.getAnnotation(ReentrantLock.class) != null),new ReentrantLockStrategy(redissonClient)),
                Case($(v -> method.getAnnotation(ReadLock.class) != null),new ReadLockStrategy(redissonClient)),
                Case($(v -> method.getAnnotation(WriteLock.class) != null),new WriteLockStrategy(redissonClient))
        );
    }

}
