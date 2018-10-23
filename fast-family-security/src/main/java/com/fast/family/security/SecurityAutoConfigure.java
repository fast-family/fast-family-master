package com.fast.family.security;

import com.fast.family.security.jwt.JWTHelper;
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

import java.util.Properties;

/**
 * @author 张顺
 * @version 1.0
 */
@Configuration
@EnableConfigurationProperties({SecurityProperties.class})
@ConditionalOnProperty(prefix = "fast.family.security",name = "enabled",havingValue = "true")
public class SecurityAutoConfigure {

    public static final String PREFIX = "fast.family.security.validate.code";


    @Bean
    public JWTHelper jwtHelper(){
        return new JWTHelper();
    }

    @Configuration
    @ConditionalOnClass(SecurityProperties.class)
    @ConditionalOnProperty(prefix = PREFIX,name = "repository",havingValue = "imMemory")
    public static class ImMemoryValidateCodeRepositoryAutoConfigure{

        @Bean
        public ValidateCodeRepository imMemoryValidateCodeRepository(){
            return new ImMemoryValidateCodeRepository();
        }

    }

    @Configuration
    @ConditionalOnClass(SecurityProperties.class)
    @ConditionalOnProperty(prefix = PREFIX,name = "repository",havingValue = "redis")
    public static class RedisValidateCodeRepositoryAutoConfigure{

        public ValidateCodeRepository redisValidateCodeRepository(){
            return new RedisValidateCodeRepository();
        }

    }


    @Configuration
    @ConditionalOnClass(SmsValidateCodeProperties.class)
    @ConditionalOnProperty(prefix = PREFIX,name = "sms",havingValue = "true",matchIfMissing = true)
    @EnableConfigurationProperties({SmsValidateCodeProperties.class})
    public static class SmsValidateCodeGeneratorAutoConfigure{

        @Bean
        public ValidateCodeGenerator smsValidateCodeGenerator(){
            return new SmsValidateCodeGenerator();
        }

    }

}
