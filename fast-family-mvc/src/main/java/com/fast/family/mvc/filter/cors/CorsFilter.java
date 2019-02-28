package com.fast.family.mvc.filter.cors;

import com.fast.family.commons.CommonProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class CorsFilter extends OncePerRequestFilter {

    private CommonProperties commonProperties;

    public CorsFilter(CommonProperties commonProperties) {
        this.commonProperties = commonProperties;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        response.addHeader("Access-Control-Allow-Origin", commonProperties.getSecuirty().getCors().getAllowOrigin());
        response.addHeader("Access-Control-Allow-Methods", commonProperties.getSecuirty().getCors().getAllowMethods());
        response.addHeader("Access-Control-Allow-Headers", commonProperties.getSecuirty().getCors().getAllowHeaders());
        response.addHeader("Access-Control-Max-Age", "3600000");
        response.addHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        chain.doFilter(request,response);
    }
}
