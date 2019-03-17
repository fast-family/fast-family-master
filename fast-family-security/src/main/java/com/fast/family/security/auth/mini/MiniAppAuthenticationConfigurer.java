package com.fast.family.core.security.auth.mini;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
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
public class MiniAppAuthenticationConfigurer extends
        SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final UserDetailsService userDetailsService;

    private final AuthenticationFailureHandler authenticationFailureHandler;

    private final AuthenticationSuccessHandler authenticationSuccessHandler;


    public MiniAppAuthenticationConfigurer(UserDetailsService userDetailsService,
                                           AuthenticationFailureHandler authenticationFailureHandler,
                                           AuthenticationSuccessHandler authenticationSuccessHandler) {
        this.userDetailsService = userDetailsService;
        this.authenticationFailureHandler = authenticationFailureHandler;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
    }

    @Override
    public void configure(HttpSecurity builder) throws Exception {
        MiniAppAuthenticationFilter miniAppAuthenticationFilter = new MiniAppAuthenticationFilter();
        miniAppAuthenticationFilter.setAuthenticationManager(builder.getSharedObject(AuthenticationManager.class));
        miniAppAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler);
        miniAppAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);

        MiniAppAuthenticationProvider miniAppAuthenticationProvider =
                new MiniAppAuthenticationProvider();
        miniAppAuthenticationProvider.setUserDetailsService(userDetailsService);
        builder.authenticationProvider(miniAppAuthenticationProvider)
                .addFilterBefore(miniAppAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
