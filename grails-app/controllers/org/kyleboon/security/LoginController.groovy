package org.kyleboon.security

import org.kyleboon.StepOneUserDetailsProviderService

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

    }
}
