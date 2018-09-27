package com.fast.family.security.handler;

import com.fast.family.commons.json.Response;
import com.fast.family.utils.GsonUtils;
import com.fast.family.utils.WebUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("customAuthenticationSuccessHandler")
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        Response logoutSuccess = new Response();
        logoutSuccess.setCode(200);
        logoutSuccess.setMessage("认证成功");
        WebUtils.writeJson(response, GsonUtils.toJson(logoutSuccess,Response.class).getBytes());
    }
}
