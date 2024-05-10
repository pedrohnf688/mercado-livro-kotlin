package com.pedrohnf688.mercadolivro.service

import com.pedrohnf688.mercadolivro.exceptions.AuthenticationException
import com.pedrohnf688.mercadolivro.repsitory.CustomerRepository
import com.pedrohnf688.mercadolivro.security.UserCustomDetails
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailsCustomService(
        private val customerRepository: CustomerRepository
): UserDetailsService {


    override fun loadUserByUsername(id: String): UserDetails {
        val customer = customerRepository.findById(id.toInt())
                .orElseThrow {AuthenticationException("Usuário não encotrado", "999")}

        return UserCustomDetails(customer)

    }
}