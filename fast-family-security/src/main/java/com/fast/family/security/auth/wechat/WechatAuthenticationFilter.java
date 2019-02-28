package com.fast.family.security.auth.wechat;

import com.fast.family.commons.utils.WebUtils;
import com.fast.family.security.SecurityConstants;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
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
public class WechatAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    @Getter
    @Setter
    private WxMpService wxMpService;

    public WechatAuthenticationFilter() {
        super(new AntPathRequestMatcher(SecurityConstants.WECHAT_AUTH_URL, "POST"));
    }

    protected WechatAuthenticationFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }

    protected WechatAuthenticationFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
        super(requiresAuthenticationRequestMatcher);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        try {
            WxMpOAuth2AccessToken token = wxMpService.oauth2getAccessToken(SecurityConstants.WECHAT_APP_CODE);
            log.debug("wechat oauth success token {}", token);
        } catch (WxErrorException e) {
            throw new InternalAuthenticationServiceException("微信授权失败", e);
        }
        WechatAuthenticationToken token = new WechatAuthenticationToken(WebUtils.getHttpServletRequest()
                .getParameter(SecurityConstants.MOBILE));
        token.setDetails(authenticationDetailsSource.buildDetails(request));
        return this.getAuthenticationManager().authenticate(token);
    }
}
