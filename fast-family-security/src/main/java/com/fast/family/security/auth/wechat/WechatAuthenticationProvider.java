package com.fast.family.security.auth.wechat;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author 张顺
 * @version 1.0
 */
@Slf4j
public class WechatAuthenticationProvider implements AuthenticationProvider {


    @Getter
    @Setter
    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        WechatAuthenticationToken token = (WechatAuthenticationToken) authentication;
        UserDetails user = userDetailsService.loadUserByUsername((String) token.getPrincipal());
        if (user == null) {
            throw new InternalAuthenticationServiceException("用户名或密码错误");
        }
        WechatAuthenticationToken result = new WechatAuthenticationToken(user.getAuthorities());
        result.setDetails(token.getCredentials());
        return result;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return WechatAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
