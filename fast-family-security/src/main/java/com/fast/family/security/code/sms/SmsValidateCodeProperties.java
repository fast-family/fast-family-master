package com.fast.family.security.code.sms;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author 张顺
 * @version 1.0
 */
@Data
@ConfigurationProperties(prefix = "fast.family.validate.code.sms")
public class SmsValidateCodeProperties {

    private int length = 6;

    private int expireIn = 60;//60秒过期
}
