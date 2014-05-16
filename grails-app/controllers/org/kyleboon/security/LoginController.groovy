package org.kyleboon.security

import grails.plugin.springsecurity.SpringSecurityUtils
import org.kyleboon.StepOneUserDetailsProviderService
import org.kyleboon.TextMessageAuthenticationFilter

import static grails.plugin.springsecurity.SpringSecurityUtils.ifAnyGranted

class LoginController extends grails.plugin.springsecurity.LoginController {
    def beforeInterceptor = {
        println "Tracing action ${actionUri}"
    }

    def denied() {
        if (ifAnyGranted(StepOneUserDetailsProviderService.ROLE_STEP_ONE_AUTHENTICATED)) {
            redirect action: 'steptwo'
        }
    }


    def steptwo() {
        [
                postUrl: "${request.contextPath}/j_spring_security_text_message",
                tokenName: TextMessageAuthenticationFilter.TEXT_MESSAGE_RESPONSE_KEY
        ]

    }
}
