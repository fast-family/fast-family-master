package com.fast.family.redis.lock.aop;

import com.fast.family.redis.lock.delegate.LockDelegate;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;


/**
 * @author 张顺
 * @version 1.0
 */
@Slf4j
public class LockMethodInterceptor implements MethodInterceptor {

    private LockDelegate lockDelegate;

    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        return lockDelegate.invoke(methodInvocation);
    }

}
