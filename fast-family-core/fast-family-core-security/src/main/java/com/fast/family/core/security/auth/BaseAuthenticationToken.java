package com.fast.family.core.security.auth;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author 张顺
 * @version 1.0
 */
public abstract class BaseAuthenticationToken extends AbstractAuthenticationToken {
    /**
     * Creates a token with the supplied array of authorities.
     *
     * @param authorities the collection of <tt>GrantedAuthority</tt>s for the principal
     *                    represented by this authentication object.
     */
    public BaseAuthenticationToken(Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
    }
}
