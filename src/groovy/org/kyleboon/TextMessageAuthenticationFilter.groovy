package org.kyleboon

import org.springframework.security.authentication.AuthenticationServiceException
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class TextMessageAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    public final static String TEXT_MESSAGE_RESPONSE_KEY = 'text_message_response'

    public TextMessageAuthenticationFilter() {
        super('/j_spring_security_text_message')
    }

    @Override
    Authentication attemptAuthentication(HttpServletRequest request,
                                         HttpServletResponse response) throws AuthenticationException {
        logger.error("Attempting text message authentication")

        if (!request.post) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: $request.method")
        }

        String userName = SecurityContextHolder.context?.authentication.principal.username
        String textMessageResponse = request.getParameter(TEXT_MESSAGE_RESPONSE_KEY)

        TextMessageAuthenticationToken authentication = new TextMessageAuthenticationToken(userName, null, textMessageResponse)
        Authentication authToken = authenticationManager.authenticate(authentication)

        return authToken
    }


}