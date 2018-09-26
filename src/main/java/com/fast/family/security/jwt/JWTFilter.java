package com.fast.family.security.jwt;

import com.fast.family.commons.web.ContentCachingRequestWrapper;
import com.fast.family.commons.web.ContentCachingResponseWrapper;
import com.fast.family.security.SecurityProperties;
import com.fast.family.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Order(1)
public class JWTFilter extends OncePerRequestFilter{

    private JWTHelper jwtHelper;

    private SecurityProperties securityProperties;

    public JWTFilter(JWTHelper jwtHelper, SecurityProperties securityProperties) {
        this.jwtHelper = jwtHelper;
        this.securityProperties = securityProperties;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(httpServletRequest);
        String jwt = jwtHelper.resolveToken(requestWrapper);
        if (StringUtils.hasText(jwt) && this.jwtHelper.validateToken(jwt)){
            Authentication authentication = this.jwtHelper.getAuthentication(jwt);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(requestWrapper, httpServletResponse);
    }
}
