package com.fast.family.security.mobile;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author 张顺
 * @version 1.0
 * @created 2018/9/27-21:22
 */
@Slf4j
public class SmsCodeAuthenticationConfigurer extends
        SecurityConfigurerAdapter<DefaultSecurityFilterChain,HttpSecurity>{


    private final UserDetailsService userDetailsService;

    public SmsCodeAuthenticationConfigurer(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void configure(HttpSecurity builder) throws Exception {
        SmsCodeAuthenticationFilter smsCodeAuthenticationFilter = new SmsCodeAuthenticationFilter();
        smsCodeAuthenticationFilter.setAuthenticationManager(builder.getSharedObject(AuthenticationManager.class));
        SmsCodeAuthenticationProvider smsCodeAuthenticationProvider = new SmsCodeAuthenticationProvider();
        smsCodeAuthenticationProvider.setUserDetailsService(userDetailsService);
        builder.authenticationProvider(smsCodeAuthenticationProvider)
                .addFilterBefore(smsCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
