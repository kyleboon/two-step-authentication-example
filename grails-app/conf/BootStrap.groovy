import org.kyleboon.Role
import org.kyleboon.User
import org.kyleboon.UserRole

class BootStrap {

    def init = { servletContext ->
        Role role1 = Role.findByAuthority(Role.ROLE_NORMAL) ?: new Role(authority: Role.ROLE_NORMAL).save(failOnError: true)
        Role.list().each { println it.authority }
        User user = new User(username: 'user1', password: 'password1').save(failOnError: true)
        UserRole.create user, role1, true

        println(user.authorities)
    }
    def destroy = {
    }
}
