package com.pedrohnf688.mercadolivro.security

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValues
import com.pedrohnf688.mercadolivro.controller.request.LoginRequest
import com.pedrohnf688.mercadolivro.exceptions.AuthenticationException
import com.pedrohnf688.mercadolivro.repsitory.CustomerRepository
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class AuthenticationFilter(
        authenticationManager: AuthenticationManager,
        private val customerRepository: CustomerRepository
): UsernamePasswordAuthenticationFilter() {

    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse?): Authentication {

        try {
            val loginRequest = jacksonObjectMapper().readValue(request.inputStream, LoginRequest::class.java)
            val id = customerRepository.findByEmail(loginRequest.email)?.id
            val authToken = UsernamePasswordAuthenticationToken(id,loginRequest.password)

            return authenticationManager.authenticate(authToken)
        }catch (ex: Exception){
            throw AuthenticationException("Falha ao autenticar", "999")
        }

    }

    override fun successfulAuthentication(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain, authResult: Authentication) {
        (authResult.principal as UserCustomDetails).id

        response.addHeader("Authrization", "123456")

    }

}