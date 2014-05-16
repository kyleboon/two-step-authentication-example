package org.kyleboon

import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService

class TextMessageAuthenticationProvider implements AuthenticationProvider {
    UserDetailsService userDetailsService

    /**
     * Much of this is copied directly from
     * {@link org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider}
     */
    Authentication authenticate(Authentication authentication) throws AuthenticationException {
        TextMessageAuthenticationToken authToken = (TextMessageAuthenticationToken) authentication
        String username = (authToken.principal == null) ? 'NONE_PROVIDED' : authToken.name
        UserDetails user = userDetailsService.loadUserByUsername(username)

        Boolean verifiedResponse = authToken.textMessageResponse == '1234'

        if (!verifiedResponse) {
            throw new WrongTextMessageResponse("Incorrect text message response from ${username}")
        }
        return createSuccessAuthentication(user, authToken)
    }

    @Override
    protected Authentication createSuccessAuthentication(Object principal, Authentication authentication) {
        // Ensure we return the original credentials the user supplied,
        // so subsequent attempts are successful even with encoded passwords.
        // Also ensure we return the original getDetails(), so that future
        // authentication events after cache expiry contain the details
        TextMessageAuthenticationToken result = new TextMessageAuthenticationToken(
                principal,
                authentication.credentials,
                principal.authorities)

        result.details = authentication.details

        return result
    }

    boolean supports(Class<? extends Object> authentication) {
        return (TextMessageAuthenticationToken.isAssignableFrom(authentication))
    }
}
