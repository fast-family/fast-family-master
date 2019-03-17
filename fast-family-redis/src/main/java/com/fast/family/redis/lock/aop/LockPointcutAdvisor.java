package com.fast.family.redis.lock.aop;

import com.fast.family.redis.lock.annotation.FairLock;
import com.fast.family.redis.lock.annotation.ReadLock;
import com.fast.family.redis.lock.annotation.ReentrantLock;
import com.fast.family.redis.lock.annotation.WriteLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;

import java.lang.reflect.Method;

/**
 * @author 张顺
 * @version 1.0
 */
@Slf4j
public class LockPointcutAdvisor extends StaticMethodMatcherPointcutAdvisor {

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        if (method.getAnnotation(ReentrantLock.class) != null ||
            method.getAnnotation(ReadLock.class) != null ||
            method.getAnnotation(WriteLock.class) != null ||
            method.getAnnotation(FairLock.class) != null){
            return true;
        }
        return false;
    }

}
