package com.pedrohnf688.mercadolivro.config

import com.pedrohnf688.mercadolivro.repsitory.CustomerRepository
import com.pedrohnf688.mercadolivro.security.AuthenticationFilter
import com.pedrohnf688.mercadolivro.service.UserDetailsCustomService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig(
        private val customerRepository: CustomerRepository,
        private val authenticationConfiguration: AuthenticationConfiguration,
        private val userDetailsCustomService: UserDetailsCustomService

) {

    private val PUBLIC_POST_MATCHERS = arrayOf("/customer")

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http
                .cors {}
                .csrf { it.disable() }
                .sessionManagement { session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
                .authorizeHttpRequests { auth ->
                    auth.requestMatchers(HttpMethod.POST, *PUBLIC_POST_MATCHERS).permitAll()
                            .anyRequest().authenticated()
                }
                .addFilter(AuthenticationFilter(authenticationManager = authenticationManager(), customerRepository))
                .build()
    }


    @Bean
    @Throws(Exception::class)
    fun authenticationManager(): AuthenticationManager {
        return authenticationConfiguration.authenticationManager
    }

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }
}