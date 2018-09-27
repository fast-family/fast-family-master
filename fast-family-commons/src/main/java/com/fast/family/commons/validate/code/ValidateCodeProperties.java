package com.fast.family.commons.validate.code;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author 张顺
 * @version 1.0
 * @created 2018/9/23-23:22
 */
@Data
@ConfigurationProperties(prefix = "fast.family.validate.code")
public class ValidateCodeProperties {


    private final SmsCodeProperties smsCodeProperties = new SmsCodeProperties();

    @Data
    public static class SmsCodeProperties{
        private int length = 6;

        private int expireIn = 60;//60秒过期
    }


}
