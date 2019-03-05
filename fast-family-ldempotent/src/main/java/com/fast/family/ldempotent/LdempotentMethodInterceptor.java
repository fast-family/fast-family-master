package com.fast.family.ldempotent;

import com.fast.family.commons.CommonProperties;
import com.fast.family.commons.exception.LdempotentException;
import com.fast.family.commons.json.ResponseCode;
import com.fast.family.commons.utils.WebUtils;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;

import java.lang.reflect.Method;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author 张顺
 * @version 1.0
 */
@Slf4j
public class LdempotentMethodInterceptor implements MethodInterceptor {

    private RedissonClient redissonClient;

    private CommonProperties commonProperties;

    public LdempotentMethodInterceptor(RedissonClient redissonClient,CommonProperties commonProperties) {
        this.redissonClient = redissonClient;
        this.commonProperties = commonProperties;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        String ldempotentToken = Optional.ofNullable(WebUtils.getHttpServletRequest().getHeader(commonProperties.getLdempotent().getLdempotentHeaderName()))
                .orElseThrow(() -> new LdempotentException(ResponseCode.LDEMPOTENT_ERROR.getCode(),ResponseCode.LDEMPOTENT_ERROR.getMessage()));
        RMapCache<String,String> rMapCache = redissonClient.getMapCache(commonProperties.getLdempotent().getLdempotentKey());
        if (rMapCache.put(commonProperties.getLdempotent().getLdempotentPrefix() + ldempotentToken,ldempotentToken,
                commonProperties.getLdempotent().getTtl(), TimeUnit.MINUTES) != null){
            throw new LdempotentException(ResponseCode.LDEMPOTENT_ERROR.getCode(),ResponseCode.LDEMPOTENT_ERROR.getMessage());
        }
        return invocation.proceed();
    }

}
