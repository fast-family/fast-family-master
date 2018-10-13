package com.fast.family.security.mobile;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 张顺
 * @version 1.0
 * @created 2018/9/27-21:22
 */
public class SmsCodeAuthenticationFilter extends AbstractAuthenticationProcessingFilter{


    @Setter
    @Getter
    private UserDetailsService userDetailsService;

    protected SmsCodeAuthenticationFilter(){
        super(new AntPathRequestMatcher("/authentication/mobile","POST"));
    }

    protected SmsCodeAuthenticationFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }

    protected SmsCodeAuthenticationFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
        super(requiresAuthenticationRequestMatcher);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        if (!request.getMethod().equals("POST")){
            throw new AuthenticationServiceException("Authentication method not supported:" + request.getMethod());
        }
        String mobile = getMobile(request);
        SmsCodeAuthenticationToken token =
                new SmsCodeAuthenticationToken(mobile);
        token.setDetails(authenticationDetailsSource.buildDetails(request));
        return this.getAuthenticationManager().authenticate(token);
    }

    private String getMobile(HttpServletRequest request){
        return request.getParameter("mobile");
    }


}
