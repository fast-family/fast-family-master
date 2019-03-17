package com.fast.family.security.auth.mini;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.fast.family.commons.utils.WebUtils;
import com.fast.family.core.security.SecurityConstants;
import com.fast.family.core.security.SecurityProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 张顺
 * @version 1.0
 */
@Slf4j
public class MiniAppAuthenticationFilter extends AbstractAuthenticationProcessingFilter {


    @Autowired
    private SecurityProperties securityProperties;

    @Getter
    @Setter
    private WxMaService wxMaService;

    public MiniAppAuthenticationFilter() {
        super(new AntPathRequestMatcher(securityProperties.getMiniApp().getMiniAppUrl(), securityProperties.getMiniApp().getHttpMethod()));
    }


    protected MiniAppAuthenticationFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }

    protected MiniAppAuthenticationFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
        super(requiresAuthenticationRequestMatcher);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException, IOException, ServletException {

        try {
            WxMaJscode2SessionResult wxResult = wxMaService.getUserService().getSessionInfo(WebUtils.getHttpServletRequest().getParameter(SecurityConstants.LOGIN_TYPE_SMS));
        } catch (WxErrorException e) {
            throw new InternalAuthenticationServiceException(e.getMessage(), e);
        }
        String mobile = WebUtils.getHttpServletRequest().getParameter(SecurityConstants.MOBILE);
        MiniAppAuthenticationToken token = new MiniAppAuthenticationToken(mobile);
        token.setDetails(authenticationDetailsSource.buildDetails(request));
        return this.getAuthenticationManager().authenticate(token);
    }
}
