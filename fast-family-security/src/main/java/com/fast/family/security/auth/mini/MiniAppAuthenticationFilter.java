package com.fast.family.security.auth.mini;

import com.fast.family.security.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 张顺
 * @version 1.0
 */
@Slf4j
public class MiniAppAuthenticationFilter extends AbstractAuthenticationProcessingFilter {


    private SecurityProperties securityProperties;

    public MiniAppAuthenticationFilter(SecurityProperties securityProperties){
        super(new AntPathRequestMatcher(securityProperties.getMiniApp().getMiniAppUrl(),securityProperties.getMiniApp().getHttpMethod()));
    }


    protected MiniAppAuthenticationFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }

    protected MiniAppAuthenticationFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
        super(requiresAuthenticationRequestMatcher);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        return null;
    }
}
