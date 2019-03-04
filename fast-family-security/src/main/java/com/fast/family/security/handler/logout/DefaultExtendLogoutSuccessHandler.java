package com.fast.family.security.handler.logout;

import com.fast.family.commons.json.Response;
import com.fast.family.commons.json.ResponseCode;
import com.fast.family.commons.utils.GsonUtils;
import com.fast.family.commons.utils.WebUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class DefaultExtendLogoutSuccessHandler implements ExtendLogoutSuccessHandler{

    @Override
    public void customOnLogoutSuccessResult(HttpServletRequest request,
                                            HttpServletResponse response,
                                            Authentication authentication) throws IOException, ServletException {
        WebUtils.writeJson(response, GsonUtils.toJson(
                Response.ok(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMessage()),Response.class).getBytes());
    }
}
