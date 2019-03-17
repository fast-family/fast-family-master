package com.fast.family.core.mvc;


import com.fast.family.commons.constant.CommonStant;
import com.fast.family.mvc.filter.log.AccessLogFilter;
import com.fast.family.mvc.generic.entity.IDProperties;
import com.fast.family.mvc.generic.entity.SnowflakeIdGenerator;
import com.fast.family.mvc.ldempotent.LdempotentMethodInterceptor;
import com.fast.family.mvc.ldempotent.LdempotentPointcutAdvisor;
import org.redisson.api.RedissonClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
@EnableConfigurationProperties({SpringMvcProperties.class, IDProperties.class})
public class SpringMvcAutoConfigure {

    private static final String PROPERTIS_PREFIX_MVC = CommonStant.PROPERTIS_PREFIX + "mvc.";

    @Bean
    public SnowflakeIdGenerator snowflakeIdGenerator(IDProperties idProperties){
        return new SnowflakeIdGenerator(idProperties);
    }

    @Bean
    @ConditionalOnProperty(prefix = PROPERTIS_PREFIX_MVC + "accessLog",name = "enabled",havingValue = "true")
    public Filter accessLogFilter(){
        return new AccessLogFilter();
    }


    @Configuration
    @ConditionalOnClass(RedissonClient.class)
    @ConditionalOnProperty(prefix = PROPERTIS_PREFIX_MVC + "ldempotent",name = "enabled",havingValue = "true")
    static class LdempotentAutoConfigure{

        @Bean
        public LdempotentMethodInterceptor ldempotentMethodInterceptor(RedissonClient redissonClient){
            return new LdempotentMethodInterceptor(redissonClient);
        }

        @Bean
        public LdempotentPointcutAdvisor ldempotentPointcutAdvisor(LdempotentMethodInterceptor ldempotentMethodInterceptor){
            LdempotentPointcutAdvisor ldempotentPointcutAdvisor =
                    new LdempotentPointcutAdvisor();
            ldempotentPointcutAdvisor.setAdvice(ldempotentMethodInterceptor);
            return ldempotentPointcutAdvisor;
        }

    }

}
