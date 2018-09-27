package com.fast.family.security.jwt;

import com.fast.family.security.SecurityProperties;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JWTConfigurer extends
        SecurityConfigurerAdapter<DefaultSecurityFilterChain,HttpSecurity> {

    private JWTHelper jwtHelper;

    private SecurityProperties securityProperties;

    public JWTConfigurer(JWTHelper jwtHelper, SecurityProperties securityProperties) {
        this.jwtHelper = jwtHelper;
        this.securityProperties = securityProperties;
    }

    @Override
    public void configure(HttpSecurity builder) throws Exception {
        JWTFilter customFilter = new JWTFilter(jwtHelper,securityProperties);
        builder.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
