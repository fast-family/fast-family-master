package com.fast.family.security.auth.wechat;

import com.fast.family.security.auth.BaseAuthenticationToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author 张顺
 * @version 1.0
 */
@Slf4j
public class WechatAuthenticationToken extends BaseAuthenticationToken {

    private Object principal;

    public WechatAuthenticationToken(Object principal){
        super(null);
        this.principal = principal;
    }

    public WechatAuthenticationToken(Collection<? extends GrantedAuthority> authorities, Object principal) {
        super(authorities);
        this.principal = principal;
    }

    /**
     * Creates a token with the supplied array of authorities.
     *
     * @param authorities the collection of <tt>GrantedAuthority</tt>s for the principal
     *                    represented by this authentication object.
     */
    public WechatAuthenticationToken(Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }
}
