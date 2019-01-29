package com.fast.family.security.auth.mini;

import com.fast.family.security.auth.BaseAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author 张顺
 * @version 1.0
 */
public class MiniAppAuthenticationToken extends BaseAuthenticationToken {
    /**
     * Creates a token with the supplied array of authorities.
     *
     * @param authorities the collection of <tt>GrantedAuthority</tt>s for the principal
     *                    represented by this authentication object.
     */
    public MiniAppAuthenticationToken(Collection<? extends GrantedAuthority> authorities) {
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
