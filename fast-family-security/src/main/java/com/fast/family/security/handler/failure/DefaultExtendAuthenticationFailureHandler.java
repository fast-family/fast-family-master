package com.fast.family.security.handler.failure;

import com.fast.family.commons.json.Response;
import com.fast.family.commons.json.ResponseCode;
import com.fast.family.commons.utils.GsonUtils;
import com.fast.family.commons.utils.WebUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class DefaultExtendAuthenticationFailureHandler implements ExtendAuthenticationFailureHandler {

    @Override
    public void customAuthenticationFailureResult(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {
        WebUtils.writeJson(response,
                GsonUtils.toJson(
                        Response.fail(ResponseCode.SERVER_ERROR.getCode(),exception.getMessage()),
                        Response.class).getBytes());
    }
}
