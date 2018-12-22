package com.fast.family.log;

import com.fast.family.log.aop.AccessLogMethodInterceptor;
import com.fast.family.log.aop.AccessLogPointcutAdvisor;
import com.fast.family.log.repository.DefaultLogRepository;
import com.fast.family.log.repository.LogRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * @author 张顺
 * @version 1.0
 */

@Configuration
public class AccessLogAutoConfigure {


    @Bean
    @ConditionalOnBean(AccessLogMethodInterceptor.class)
    public AccessLogPointcutAdvisor accessLogPointcutAdvisor(AccessLogMethodInterceptor accessLogMethodInterceptor){
        AccessLogPointcutAdvisor accessLogPointcutAdvisor =
                new AccessLogPointcutAdvisor();
        accessLogPointcutAdvisor.setAdvice(accessLogMethodInterceptor);
        return accessLogPointcutAdvisor;
    }


    @Configuration
    @ConditionalOnProperty(prefix = "fast.family.log.storage",name = "storage",havingValue = "InMemory")
    public static class DefaultLogRepositoryAutoConfiguration{

        @Bean
        public LogRepository defalutRepository(){
            return new DefaultLogRepository();
        }

    }



}
