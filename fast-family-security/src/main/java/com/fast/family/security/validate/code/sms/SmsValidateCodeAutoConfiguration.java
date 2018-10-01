package com.fast.family.security.validate.code.sms;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author 张顺
 * @version 1.0
 * @created 2018/9/28-23:51
 */
@Configuration
@ConditionalOnProperty(name = "fast.family.validate.code.sms.enabled",havingValue = "true")
@EnableConfigurationProperties({SmsValidateCodeProperties.class})
public class SmsValidateCodeAutoConfiguration {
}
