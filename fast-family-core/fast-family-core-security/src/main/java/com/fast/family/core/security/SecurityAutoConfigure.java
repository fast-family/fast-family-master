package com.fast.family.security;

import com.fast.family.commons.constant.CommonStant;
import com.fast.family.security.handler.failure.DefaultExtendAuthenticationFailureHandler;
import com.fast.family.security.handler.failure.ExtendAuthenticationFailureHandler;
import com.fast.family.security.handler.logout.DefaultExtendLogoutSuccessHandler;
import com.fast.family.security.handler.logout.ExtendLogoutSuccessHandler;
import com.fast.family.security.handler.success.DefaultExtendAuthenticationSuccessHandler;
import com.fast.family.security.handler.success.ExtendAuthenticationSuccessHandler;
import com.fast.family.security.validate.code.ImMemoryValidateCodeRepository;
import com.fast.family.security.validate.code.RedisValidateCodeRepository;
import com.fast.family.security.validate.code.ValidateCodeGenerator;
import com.fast.family.security.validate.code.ValidateCodeRepository;
import com.fast.family.security.validate.code.sms.SmsValidateCodeGenerator;
import com.fast.family.security.validate.code.sms.SmsValidateCodeProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 张顺
 * @version 1.0
 */
@Configuration
@EnableConfigurationProperties({SecurityProperties.class})
@ConditionalOnProperty(prefix = CommonStant.PROPERTIS_PREFIX + "security", name = "enabled", havingValue = "true")
public class SecurityAutoConfigure {

    private static final String PROPERTIS_PREFIX_SECURITY_CODE = CommonStant.PROPERTIS_PREFIX + "security.validate.code";


    @Bean
    public ExtendAuthenticationSuccessHandler extendAuthenticationSuccessHandler() {
        return new DefaultExtendAuthenticationSuccessHandler();
    }

    @Bean
    public ExtendAuthenticationFailureHandler extendAuthenticationFailureHandler(){
        return new DefaultExtendAuthenticationFailureHandler();
    }

    @Bean
    public ExtendLogoutSuccessHandler extendLogoutSuccessHandler(){
        return new DefaultExtendLogoutSuccessHandler();
    }

    @Configuration
    @ConditionalOnClass(SecurityProperties.class)
    @ConditionalOnProperty(prefix = PROPERTIS_PREFIX_SECURITY_CODE, name = "repository", havingValue = "imMemory")
    public static class ImMemoryValidateCodeRepositoryAutoConfigure {

        @Bean
        public ValidateCodeRepository imMemoryValidateCodeRepository() {
            return new ImMemoryValidateCodeRepository();
        }

    }

    @Configuration
    @ConditionalOnClass(SecurityProperties.class)
    @ConditionalOnProperty(prefix = PROPERTIS_PREFIX_SECURITY_CODE, name = "repository", havingValue = "redis")
    public static class RedisValidateCodeRepositoryAutoConfigure {

        public ValidateCodeRepository redisValidateCodeRepository() {
            return new RedisValidateCodeRepository();
        }

    }


    @Configuration
    @ConditionalOnClass(SmsValidateCodeProperties.class)
    @ConditionalOnProperty(prefix = PROPERTIS_PREFIX_SECURITY_CODE, name = "sms", havingValue = "true", matchIfMissing = true)
    @EnableConfigurationProperties({SmsValidateCodeProperties.class})
    public static class SmsValidateCodeGeneratorAutoConfigure {

        @Bean
        public ValidateCodeGenerator smsValidateCodeGenerator() {
            return new SmsValidateCodeGenerator();
        }

    }

}
