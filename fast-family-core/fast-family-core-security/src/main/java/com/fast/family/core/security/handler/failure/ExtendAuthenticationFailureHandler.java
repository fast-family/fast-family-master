package com.fast.family.core.security.handler.failure;

import org.springframework.security.core.AuthenticationException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ExtendAuthenticationFailureHandler {

    void customAuthenticationFailureResult(HttpServletRequest request,
                                           HttpServletResponse response,
                                           AuthenticationException exception) throws IOException, ServletException;
}
