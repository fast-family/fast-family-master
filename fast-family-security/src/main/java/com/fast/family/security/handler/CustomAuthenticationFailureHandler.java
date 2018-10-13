package com.fast.family.security.handler;

import com.fast.family.commons.json.Response;
import com.fast.family.commons.utils.GsonUtils;
import com.fast.family.utils.WebUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("customAuthenticationFailureHandler")
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler{


    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        Response logoutSuccess = new Response();
        logoutSuccess.setCode(401);
        logoutSuccess.setMessage("认证失败请重新登录");
        WebUtils.writeJson(response, GsonUtils.toJson(logoutSuccess,Response.class).getBytes());
    }
}
