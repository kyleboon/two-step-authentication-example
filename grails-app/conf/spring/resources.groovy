import org.springframework.security.authentication.dao.DaoAuthenticationProvider

// Place your Spring DSL code here
beans = {
    daoAuthenticationProvider(DaoAuthenticationProvider) {
        it.autowire = true
        userDetailsService = ref('stepOneUserDetailsProviderService')
    }
}
