package com.fast.family.security.validate.code.sms;

import com.fast.family.commons.utils.DateTimeUtils;
import com.fast.family.commons.utils.RandomUtils;
import com.fast.family.security.validate.code.ValidateCode;
import com.fast.family.security.validate.code.ValidateCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 张顺
 * @version 1.0
 * @created 2018/9/23-23:19
 */
@Component
public class SmsValidateCodeGenerator implements ValidateCodeGenerator {

    @Autowired
    private SmsValidateCodeProperties smsValidateCodeProperties;

    @Override
    public ValidateCode generate() {
        String code = RandomUtils.genRandom(
                smsValidateCodeProperties.getLength(),10);
        return new ValidateCode(code, DateTimeUtils.convertLDTToLong(DateTimeUtils.parserDateTime()
                .withMinute(smsValidateCodeProperties.getExpireIn())));
    }
}
