import grails.plugin.springsecurity.SpringSecurityUtils
import org.kyleboon.AuthenticationSuccessHandler
import org.kyleboon.TextMessageAuthenticationFilter
import org.kyleboon.TextMessageAuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider

// Place your Spring DSL code here
beans = {
    authenticationSuccessHandler(AuthenticationSuccessHandler) {
        it.autowire = true
    }

    daoAuthenticationProvider(DaoAuthenticationProvider) {
        it.autowire = true
        userDetailsService = ref('stepOneUserDetailsProviderService')
    }

    textMessageAuthenticationProvider(TextMessageAuthenticationProvider) {
        it.autowire = true
        userDetailsService  = ref('userDetailsService')
    }

    textMessageAuthenticationFilter(TextMessageAuthenticationFilter) {
        it.autowire = true
        filterProcessesUrl = SpringSecurityUtils.securityConfig.textMessage.filterProcessesUrl
    }
}
