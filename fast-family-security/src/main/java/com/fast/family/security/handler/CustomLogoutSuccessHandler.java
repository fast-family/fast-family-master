package com.fast.family.security.handler;

import com.fast.family.commons.utils.GsonUtils;
import com.fast.family.commons.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("customLogoutSuccessHandler")
public class CustomLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler{

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                                Authentication authentication) throws IOException, ServletException {

        WebUtils.writeJson(response, GsonUtils.toJson(
                ResponseEntity.status(HttpStatus.OK).build(),ResponseEntity.class).getBytes());
    }
}
