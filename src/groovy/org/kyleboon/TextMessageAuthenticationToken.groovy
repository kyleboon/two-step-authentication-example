package org.kyleboon

import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.GrantedAuthority

class TextMessageAuthenticationToken extends AbstractAuthenticationToken {
    String textMessageResponse
    Object credentials
    Object principal

    TextMessageAuthenticationToken(Object principal, Object credentials, String textMessageResponse) {
        super(null)
        this.principal = principal
        this.credentials = credentials
        this.textMessageResponse = textMessageResponse
        this.setAuthenticated(false)
    }

    TextMessageAuthenticationToken(Object principal,
                                   Object credentials,
                                   Collection<? extends GrantedAuthority> authorities) {
        super(authorities)
        this.principal = principal
        this.credentials = credentials
        super.setAuthenticated(true)
    }

    @Override
    void setAuthenticated(boolean isAuthenticated) {
        if (isAuthenticated) {
            throw new IllegalArgumentException(
                    'Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead')
        }
        super.setAuthenticated(false)
    }

    @Override
    public Object getCredentials() {
        return credentials
    }

    @Override
    public Object getPrincipal() {
        return principal
    }
}
