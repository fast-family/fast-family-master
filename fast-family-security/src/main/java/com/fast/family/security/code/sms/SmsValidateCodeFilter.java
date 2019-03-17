package com.fast.family.core.security.validate.code.sms;

import com.fast.family.commons.exception.ValidateCodeException;
import com.fast.family.commons.utils.GsonUtils;
import com.fast.family.commons.utils.WebUtils;
import com.fast.family.security.SecurityConstants;
import com.fast.family.security.validate.code.ValidateCode;
import com.fast.family.security.validate.code.ValidateCodeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * @author 张顺
 * @version 1.0
 */
@Slf4j
public class SmsValidateCodeFilter extends OncePerRequestFilter {


    @Autowired
    private ValidateCodeRepository validateCodeRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            validate(request);
        } catch (ValidateCodeException e) {
            log.error(e.getErrMessage(), e);
            WebUtils.writeJson(response, GsonUtils.toJson(
                    ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(), ResponseEntity.class).getBytes());
            return;
        }
        filterChain.doFilter(request, response);

    }

    private void validate(HttpServletRequest request) {
        String loginType = request.getHeader(SecurityConstants.LOGIN_TYPE);
        if (loginType != null && loginType.equals(SecurityConstants.LOGIN_TYPE_SMS)) {
            String smsValidateCode = Optional.ofNullable(request.getParameter("smsValidateCode"))
                    .orElseThrow(() -> new ValidateCodeException("验证码不存在"));
            ValidateCode validateCode = Optional.ofNullable(validateCodeRepository.get(smsValidateCode))
                    .orElseThrow(() -> new ValidateCodeException("验证码不存在"));
            if (System.currentTimeMillis() > validateCode.getExpireTime()) {
                throw new ValidateCodeException("验证码已过期");
            }
        }
    }

}
