package com.fast.family.security.auth.mini;

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
public class MiniAppAuthenticationProvider  implements AuthenticationProvider {

    @Getter
    @Setter
    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        MiniAppAuthenticationToken token = (MiniAppAuthenticationToken) authentication;
        UserDetails userDetails = userDetailsService.loadUserByUsername((String) token.getPrincipal());
        if (userDetails == null){
            throw new InternalAuthenticationServiceException("用户名或密码错误");
        }
        MiniAppAuthenticationToken result = new MiniAppAuthenticationToken(userDetails.getAuthorities());
        return result;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return MiniAppAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
