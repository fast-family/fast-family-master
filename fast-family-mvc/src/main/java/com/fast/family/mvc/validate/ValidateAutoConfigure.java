package com.fast.family.mvc.validate;


import com.fast.family.mvc.validate.aop.ValidateInterceptor;
import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 张顺
 * @version 1.0
 */
@Configuration
public class ValidateAutoConfigure {


    @Bean
    public SimpleValidatorHandler validatorHandler(){
        return new SimpleValidatorHandler();
    }

    @Bean
    public MethodInterceptor validateMethodInterceptor(){
        return new ValidateInterceptor();
    }
}
