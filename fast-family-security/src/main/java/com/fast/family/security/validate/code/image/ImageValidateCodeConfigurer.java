package com.fast.family.security.validate.code.image;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

/**
 * @author 张顺
 * @version 1.0
 * @created 2018/10/14-17:25
 */
@Slf4j
public class ImageValidateCodeConfigurer extends
        SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {


    @Override
    public void configure(HttpSecurity builder) throws Exception {
        ImageValidateCodeFilter imageValidateCodeFilter = new ImageValidateCodeFilter();
        builder.addFilterBefore(imageValidateCodeFilter, AbstractPreAuthenticatedProcessingFilter.class);
    }
}
