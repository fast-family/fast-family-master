package com.fast.family.ldempotent;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;

import java.lang.reflect.Method;
import java.util.Optional;

/**
 * @author 张顺
 * @version 1.0
 */
@Slf4j
public class LdempotentPointcutAdvisor extends StaticMethodMatcherPointcutAdvisor {


    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return Optional.ofNullable(method.getAnnotation(Ldempotent.class)).isPresent();
    }

}
