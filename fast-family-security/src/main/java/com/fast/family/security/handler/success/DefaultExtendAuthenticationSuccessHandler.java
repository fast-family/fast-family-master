package com.fast.family.security.handler.success;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
@Slf4j
public class DefaultExtendAuthenticationSuccessHandler implements ExtendAuthenticationSuccessHandler {


    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void customAuthenticationSuccessResult(HttpServletResponse response,
                                                  OAuth2AccessToken oAuth2AccessToken,
                                                  Authentication authentication) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.getWriter().write(objectMapper.writeValueAsString(oAuth2AccessToken));
    }
}
