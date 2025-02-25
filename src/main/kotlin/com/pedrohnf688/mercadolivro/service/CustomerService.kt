package com.pedrohnf688.mercadolivro.service

import com.pedrohnf688.mercadolivro.enums.CustomerStatus
import com.pedrohnf688.mercadolivro.enums.Errors
import com.pedrohnf688.mercadolivro.enums.Role
import com.pedrohnf688.mercadolivro.exceptions.NotFoundException
import com.pedrohnf688.mercadolivro.model.CustomerModel
import com.pedrohnf688.mercadolivro.repsitory.CustomerRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class CustomerService(
        private val customerRepository: CustomerRepository,
        private val bookService: BookService,
        private val bCrypt: BCryptPasswordEncoder
) {

    fun getAll(name: String?): List<CustomerModel> {
        name?.let {
            return customerRepository.findByNameContaining(it)
        }

        return customerRepository.findAll().toList()
    }

    fun create(customer: CustomerModel) {

        val customerCopy = customer.copy(
                roles = setOf(Role.CUSTOMER),
                password = bCrypt.encode(customer.password)
        )

        customerRepository.save(customerCopy)
    }

    fun findById(id: Int): CustomerModel {
        return customerRepository.findById(id).orElseThrow{ NotFoundException(Errors.MlOOO2.message.format(id), Errors.MlOOO2.code) }
    }

    fun update(customer: CustomerModel) {
        if(!customerRepository.existsById(customer.id!!)) {
            throw NotFoundException(Errors.MlOOO2.message.format(customer.id), Errors.MlOOO2.code)
        }

        customerRepository.save(customer)
    }

    fun delete(id: Int) {
        val customer = findById(id)
        bookService.deleteByCustomer(customer)

        customer.status = CustomerStatus.INVATIVO

        customerRepository.save(customer)
    }

    fun emailAvailable(email: String): Boolean {
        return !customerRepository.existsByEmail(email)
    }

}