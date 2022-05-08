package code.lumiseven.demo.skt.springsecurity.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class WebSecurityConfig {

    /**
     * form
     */
//    @Bean
//    @Throws(Exception::class)
//    fun formSecurityChain(http: HttpSecurity): SecurityFilterChain? {
//        http
////            .antMatcher("/**")
//            .authorizeRequests()
//            .antMatchers("/**")
//            .authenticated()
//            .and()
//            .formLogin()
//            .loginPage("/login")
//            .permitAll()
//            .and()
//            .csrf().disable()
//
//        return http.build()
//    }

    /**
     * http basic
     */
    @Bean
    @Throws(Exception::class)
    fun basicSecurityChain(http: HttpSecurity): SecurityFilterChain? {
        http.csrf().disable()
        http.authorizeRequests()
            .anyRequest()
            .authenticated()
            .and()
            .httpBasic();
        return http.build()
    }

}