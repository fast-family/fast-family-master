package com.fast.family.log;

import com.fast.family.log.aop.AccessLogMethodInterceptor;
import com.fast.family.log.aop.AccessLogPointcutAdvisor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * @author 张顺
 * @version 1.0
 * @created 2018/10/17-21:59
 */
@ConditionalOnBean(AccessLogMethodInterceptor.class)
@Configuration
public class AccessLogAutoConfigure {




    @Bean
    public AccessLogPointcutAdvisor accessLogPointcutAdvisor(AccessLogMethodInterceptor accessLogMethodInterceptor){
        AccessLogPointcutAdvisor accessLogPointcutAdvisor =
                new AccessLogPointcutAdvisor();
        accessLogPointcutAdvisor.setAdvice(accessLogMethodInterceptor);
        return accessLogPointcutAdvisor;
    }

}
