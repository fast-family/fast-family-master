package com.fast.family.core.security.auth.wechat;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * @author 张顺
 * @version 1.0
 */
@Component
@Slf4j
public class WechatAuthenticationConfigurer extends
        SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final WxMpService wxMpService;

    private final UserDetailsService userDetailsService;

    private final AuthenticationFailureHandler authenticationFailureHandler;

    private final AuthenticationSuccessHandler authenticationSuccessHandler;

    public WechatAuthenticationConfigurer(WxMpService wxMpService, UserDetailsService userDetailsService,
                                          AuthenticationFailureHandler authenticationFailureHandler,
                                          AuthenticationSuccessHandler authenticationSuccessHandler) {
        this.wxMpService = wxMpService;
        this.userDetailsService = userDetailsService;
        this.authenticationFailureHandler = authenticationFailureHandler;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
    }

    @Override
    public void configure(HttpSecurity builder) throws Exception {
        WechatAuthenticationFilter wechatAuthenticationFilter = new WechatAuthenticationFilter();
        wechatAuthenticationFilter.setWxMpService(wxMpService);
        wechatAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler);
        wechatAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        WechatAuthenticationProvider wechatAuthenticationProvider = new WechatAuthenticationProvider();
        wechatAuthenticationProvider.setUserDetailsService(userDetailsService);
        builder.authenticationProvider(wechatAuthenticationProvider);
        builder.addFilterBefore(wechatAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
