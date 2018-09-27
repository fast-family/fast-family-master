package com.fast.family.commons.validate.code.sms;

import com.fast.family.commons.validate.code.ValidateCode;
import com.fast.family.commons.validate.code.ValidateCodeGenerator;
import com.fast.family.commons.validate.code.ValidateCodeProperties;
import com.fast.family.utils.DateTimeUtils;
import com.fast.family.utils.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 张顺
 * @version 1.0
 * @created 2018/9/23-23:19
 */
@Component
public class SmsValidateCodeGenerator implements ValidateCodeGenerator{

    @Autowired
    private ValidateCodeProperties validateCodeProperties;

    @Override
    public ValidateCode generate() {
        String code = RandomUtils.genRandom(
                validateCodeProperties.getSmsCodeProperties().getLength(),10);
        return new ValidateCode(code, DateTimeUtils.convertLDTToLong(DateTimeUtils.parserDateTime()
                .withMinute(validateCodeProperties.getSmsCodeProperties().getExpireIn())));
    }
}
