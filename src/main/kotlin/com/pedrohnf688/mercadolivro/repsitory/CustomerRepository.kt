package com.pedrohnf688.mercadolivro.repsitory

import com.pedrohnf688.mercadolivro.model.CustomerModel
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository: CrudRepository<CustomerModel, Int> {
    fun findByNameContaining(name: String): List<CustomerModel>
    fun existsByEmail(email: String): Boolean
    fun findByEmail(email: String): CustomerModel?
}