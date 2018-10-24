package com.fast.family.third.poarty.sms;

import com.fast.family.third.poarty.sms.ali.DefaultAliSmsTemplate;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 张顺
 * @version 1.0
 */
@Configuration
@EnableConfigurationProperties({SmsProperties.class})
public class SmsAutoConfigure {

    @Bean
    public SmsTemplate aliSmsTemplate(SmsProperties properties){
        return new DefaultAliSmsTemplate(properties.getAli());
    }

}
