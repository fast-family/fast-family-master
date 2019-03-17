package com.fast.family.security.handler.success;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ExtendAuthenticationSuccessHandler {

    void customAuthenticationSuccessResult(HttpServletResponse response,
                                           OAuth2AccessToken oAuth2AccessToken,
                                           Authentication authentication) throws IOException;

}
