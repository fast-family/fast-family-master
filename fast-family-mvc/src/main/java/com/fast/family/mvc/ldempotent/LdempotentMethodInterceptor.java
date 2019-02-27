package com.fast.family.mvc.ldempotent;

import com.fast.family.commons.exception.LdempotentException;
import com.fast.family.commons.json.ResponseCode;
import com.fast.family.commons.utils.WebUtils;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;

import java.lang.reflect.Method;
import java.util.Optional;

/**
 * @author 张顺
 * @version 1.0
 */
@Slf4j
public class LdempotentMethodInterceptor implements MethodInterceptor {

    private RedissonClient redissonClient;

    public LdempotentMethodInterceptor(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    private static final String LDEMPOTENT_TOKEN = "Ldempotent_token";

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Method method = invocation.getMethod();
        LdempotentAnnotation annotation = method.getAnnotation(LdempotentAnnotation.class);
        if (annotation != null){
            String ldempotentToken = Optional.ofNullable(WebUtils.getHttpServletRequest().getHeader(LDEMPOTENT_TOKEN))
                    .orElseThrow(() -> new LdempotentException(ResponseCode.LDEMPOTENT_ERROR.getCode(),ResponseCode.LDEMPOTENT_ERROR.getMessage()));
            RMap<String,String> rMap = redissonClient.getMap(LDEMPOTENT_TOKEN);
            Optional.ofNullable(rMap.get(ldempotentToken))
                    .orElseThrow(() -> new LdempotentException(ResponseCode.LDEMPOTENT_ERROR.getCode(),ResponseCode.LDEMPOTENT_ERROR.getMessage()));
        }
        return invocation.proceed();
    }

}
