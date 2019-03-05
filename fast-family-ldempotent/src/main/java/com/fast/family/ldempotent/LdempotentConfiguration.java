package com.fast.family.ldempotent;

import com.fast.family.commons.CommonProperties;
import com.fast.family.commons.constant.CommonStant;
import org.redisson.api.RedissonClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 张顺
 * @version 1.0
 */
@Configuration
@ConditionalOnBean(RedissonClient.class)
@ConditionalOnProperty(prefix = CommonStant.PROPERTIS_PREFIX + "ldempotent",name = "enabled",havingValue = "true")
public class LdempotentConfiguration {

    @Bean
    public LdempotentMethodInterceptor ldempotentMethodInterceptor(RedissonClient redissonClient, CommonProperties commonProperties){
        return new LdempotentMethodInterceptor(redissonClient,commonProperties);
    }

    @Bean
    public LdempotentPointcutAdvisor ldempotentPointcutAdvisor(RedissonClient redissonClient, CommonProperties commonProperties){
        LdempotentPointcutAdvisor advisor = new LdempotentPointcutAdvisor();
        advisor.setAdvice(ldempotentMethodInterceptor(redissonClient,commonProperties));
        return advisor;
    }
}
