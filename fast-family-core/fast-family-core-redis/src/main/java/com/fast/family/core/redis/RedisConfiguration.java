package com.fast.family.core.redis;

import com.fast.family.commons.constant.CommonStant;
import com.fast.family.core.redis.lock.aop.LockMethodInterceptor;
import com.fast.family.core.redis.lock.aop.LockPointcutAdvisor;
import com.fast.family.core.redis.lock.delegate.LockDelegate;
import com.fast.family.core.redis.lock.delegate.SimpleLockDelegate;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 张顺
 * @version 1.0
 */
@Configuration
@ConditionalOnClass(RedissonClient.class)
public class RedisConfiguration {



    @Configuration
    @ConditionalOnProperty(prefix = CommonStant.PROPERTIS_PREFIX + "redis.lock",name = "enabled",havingValue = "true")
    static class RedisLockAutoConfiguration{

        @Bean
        public LockMethodInterceptor lockMethodInterceptor(){
            return new LockMethodInterceptor();
        }

        @Bean
        public LockPointcutAdvisor lockPointcutAdvisor(){
            LockPointcutAdvisor advisor = new LockPointcutAdvisor();
            advisor.setAdvice(lockMethodInterceptor());
            return advisor;
        }

        @Bean
        public LockDelegate lockDelegate(RedissonClient redissonClient){
            return new SimpleLockDelegate(redissonClient);
        }
    }
}
