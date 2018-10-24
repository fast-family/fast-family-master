package com.fast.family.third.poarty.sms;

import com.fast.family.third.poarty.sms.ali.AliSmsProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author 张顺
 * @version 1.0
 */
@Data
@ConfigurationProperties(prefix = "fast.family.sms")
public class SmsProperties {

    private AliSmsProperties ali;

}
