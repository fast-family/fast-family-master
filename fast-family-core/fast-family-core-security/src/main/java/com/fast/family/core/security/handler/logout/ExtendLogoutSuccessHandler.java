package com.fast.family.security.handler.logout;

import org.springframework.security.core.Authentication;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ExtendLogoutSuccessHandler {

    void customOnLogoutSuccessResult(HttpServletRequest request, HttpServletResponse response,
                                     Authentication authentication) throws IOException, ServletException;

}
