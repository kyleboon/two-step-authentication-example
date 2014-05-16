package org.kyleboon

import groovy.util.logging.Slf4j
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler

import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Slf4j
class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response,
                                        final Authentication authentication) throws ServletException, IOException {

        log.info "Successful login event triggered: ${authentication.principal.username}"

        def session = request.getSession()
        session.trackAfterLogin = true

        super.onAuthenticationSuccess(request, response, authentication)
    }
}