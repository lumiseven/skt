package code.lumiseven.demo.skt.springsecurity.security

import code.lumiseven.demo.skt.jpa.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.stereotype.Component

@Component
class CustomAuthProvider: AuthenticationProvider {

    @Autowired
    private lateinit var userRepository: UserRepository

    private val auth = mapOf(Pair("test03", "ROLE_USER"), Pair("test02", "ROLE_ADMIN"))

    override fun authenticate(authentication: Authentication): Authentication? {
        val name = authentication.name
        val password = authentication.credentials.toString()

        val user = userRepository.findByUserName(name)

        if (user?.password == password) {
            val authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(auth[name])
            return UsernamePasswordAuthenticationToken(name, password, authorities)
        }
        return null
    }

    override fun supports(authentication: Class<*>?): Boolean {
        return true
    }

}