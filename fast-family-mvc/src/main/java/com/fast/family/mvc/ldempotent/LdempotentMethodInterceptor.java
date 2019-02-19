package com.fast.family.mvc.ldempotent;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;

/**
 * @author 张顺
 * @version 1.0
 */
@Slf4j
public class LdempotentMethodInterceptor implements MethodInterceptor {


    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Method method = invocation.getMethod();
        LdempotentAnnotation annotation = method.getAnnotation(LdempotentAnnotation.class);
        return null;
    }

}
