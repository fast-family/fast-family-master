package com.fast.family.core.redis.lock.delegate;

import org.aopalliance.intercept.MethodInvocation;

/**
 * @author 张顺
 * @version 1.0
 */
public interface LockDelegate {

    Object invoke(MethodInvocation invocation) throws Throwable;

}
