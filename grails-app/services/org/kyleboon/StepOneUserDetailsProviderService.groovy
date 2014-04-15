package org.kyleboon

import grails.plugin.springsecurity.userdetails.GormUserDetailsService
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class StepOneUserDetailsProviderService extends GormUserDetailsService {

    public static final String ROLE_STEP_ONE_AUTHENTICATED = "ROLE_PRE_AUTH"
    public static final List<GrantedAuthority> PRE_AUTH_ROLES = [new SimpleGrantedAuthority(ROLE_STEP_ONE_AUTHENTICATED)]

    @Override
    protected UserDetails createUserDetails(def user, Collection<GrantedAuthority> authorities) {
        return super.createUserDetails(user, PRE_AUTH_ROLES)
    }
}
