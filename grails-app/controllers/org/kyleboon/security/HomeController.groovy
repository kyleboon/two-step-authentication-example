package org.kyleboon.security

import grails.plugin.springsecurity.annotation.Secured
import org.kyleboon.Role

class HomeController {
    def beforeInterceptor = {
        println "Tracing action ${actionUri}"
    }

    @Secured([Role.ROLE_NORMAL])
    def index() {
    }
}
