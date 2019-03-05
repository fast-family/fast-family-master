package com.fast.family.core.security.auth.mini;


import com.fast.family.core.security.auth.BaseAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author 张顺
 * @version 1.0
 */
public class MiniAppAuthenticationToken extends BaseAuthenticationToken {


    private final Object principal;

    public MiniAppAuthenticationToken(Object principal) {
        super(null);
        this.principal = principal;
    }

    /**
     * Creates a token with the supplied array of authorities.
     *
     * @param authorities the collection of <tt>GrantedAuthority</tt>s for the principal
     *                    represented by this authentication object.
     * @param principal
     */
    public MiniAppAuthenticationToken(Collection<? extends GrantedAuthority> authorities, Object principal) {
        super(authorities);
        this.principal = principal;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }
}
