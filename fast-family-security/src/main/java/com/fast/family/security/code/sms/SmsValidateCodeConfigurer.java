package com.fast.family.core.security.validate.code.sms;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.stereotype.Component;

/**
 * @author 张顺
 * @version 1.0
 */
@Component
@Slf4j
public class SmsValidateCodeConfigurer extends
        SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {


    @Override
    public void configure(HttpSecurity builder) throws Exception {
        SmsValidateCodeFilter smsValidateCodeFilter = new SmsValidateCodeFilter();
        builder.addFilterBefore(smsValidateCodeFilter, AbstractPreAuthenticatedProcessingFilter.class);
    }
}
