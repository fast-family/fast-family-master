package com.fast.family.security;

import com.fast.family.security.validate.code.ImMemoryValidateCodeRepository;
import com.fast.family.security.validate.code.RedisValidateCodeRepository;
import com.fast.family.security.validate.code.ValidateCodeGenerator;
import com.fast.family.security.validate.code.ValidateCodeRepository;
import com.fast.family.security.validate.code.image.ImageValidateCodeGenerator;
import com.fast.family.security.validate.code.image.ImageValidateCodeProperties;
import com.fast.family.security.validate.code.sms.SmsValidateCodeGenerator;
import com.fast.family.security.validate.code.sms.SmsValidateCodeProperties;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @author 张顺
 * @version 1.0
 * @created 2018/9/23-16:18
 */
@Configuration
@EnableConfigurationProperties({SecurityProperties.class})
public class SecurityAutoConfigure {


    private static final String PREFIX = "fast.family.validate.code";


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
    @ConditionalOnClass(ImageValidateCodeProperties.class)
    @ConditionalOnProperty(prefix = PREFIX,name = "validateCode",havingValue = "image")
    @EnableConfigurationProperties({ImageValidateCodeProperties.class})
    public static class ImageValidateCodeGeneratorAutoConfigure{

        @Bean
        public DefaultKaptcha defaultKaptcha(ImageValidateCodeProperties codeProperties){
            DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
            Properties properties = new Properties();
            properties.setProperty("kaptcha.border",codeProperties.getBorder().getBorder());
            properties.setProperty("kaptcha.border.color",codeProperties.getBorder().getColor());
            properties.setProperty("kaptcha.border.thickness",codeProperties.getBorder().getThickness());
            properties.setProperty("kaptcha.producer.impl",codeProperties.getProducer().getImpl());
            properties.setProperty("kaptcha.textproducer.impl",codeProperties.getTextProducer().getImpl());
            properties.setProperty("kaptcha.textproducer.char.string",codeProperties.getTextProducer().getCharStr());
            properties.setProperty("kaptcha.textproducer.char.length",codeProperties.getTextProducer().getCharLength());
            properties.setProperty("kaptcha.textproducer.font.names",codeProperties.getTextProducer().getFontNames());
            properties.setProperty("kaptcha.textproducer.font.size",codeProperties.getTextProducer().getFontSize());
            properties.setProperty("kaptcha.textproducer.font.color",codeProperties.getTextProducer().getFontColor());
            properties.setProperty("kaptcha.textproducer.char.space",codeProperties.getTextProducer().getCharSpace());
            properties.setProperty("kaptcha.noise.impl",codeProperties.getNoise().getImpl());
            properties.setProperty("kaptcha.noise.color",codeProperties.getNoise().getColor());
            properties.setProperty("kaptcha.obscurificator.impl",codeProperties.getObscurificator().getImpl());
            properties.setProperty("kaptcha.word.impl",codeProperties.getWord().getImpl());
            properties.setProperty("kaptcha.background.impl",codeProperties.getBackground().getImpl());
            properties.setProperty("kaptcha.background.clear.from",codeProperties.getBackground().getClearFrom());
            properties.setProperty("kaptcha.background.clear.to",codeProperties.getBackground().getClearto());
            properties.setProperty("kaptcha.image.width",codeProperties.getImage().getWidth());
            properties.setProperty("kaptcha.image.height",codeProperties.getImage().getHeight());
            Config config = new Config(properties);
            defaultKaptcha.setConfig(config);
            return defaultKaptcha;
        }

        @Bean
        public ValidateCodeGenerator imageValidateCodeGenerator(){
            return new ImageValidateCodeGenerator();
        }

    }

    @Configuration
    @ConditionalOnClass(SmsValidateCodeProperties.class)
    @ConditionalOnProperty(prefix = PREFIX,name = "validateCode",havingValue = "sms",matchIfMissing = true)
    @EnableConfigurationProperties({SmsValidateCodeProperties.class})
    public static class SmsValidateCodeGeneratorAutoConfigure{

        @Bean
        public ValidateCodeGenerator smsValidateCodeGenerator(){
            return new SmsValidateCodeGenerator();
        }

    }

}
