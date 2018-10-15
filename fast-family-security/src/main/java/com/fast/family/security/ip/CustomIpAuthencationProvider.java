package com.fast.family.security.ip;

import com.fast.family.commons.exception.NoAuthException;
import com.fast.family.security.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CustomIpAuthencationProvider implements AuthenticationProvider{

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        WebAuthenticationDetails details = (WebAuthenticationDetails) authentication.getDetails();
        if (securityProperties.getExcludeIps() != null && securityProperties.getExcludeIps().length > 0){
            for (String excludeIp : securityProperties.getExcludeIps()){
                if (excludeIp.equals(details.getRemoteAddress())){
                    return authentication;
                }
            }
            throw new NoAuthException(401,"认证失败");
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
