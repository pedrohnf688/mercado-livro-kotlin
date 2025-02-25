package com.pedrohnf688.mercadolivro.repository

import com.pedrohnf688.mercadolivro.helper.buildCustomer
import com.pedrohnf688.mercadolivro.repsitory.CustomerRepository
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@ExtendWith(MockKExtension::class)
class CustomerRepositoryTest {

    @Autowired
    private lateinit var customerRepository: CustomerRepository

    @BeforeEach
    fun setup() = customerRepository.deleteAll()

    @Test
    fun `should return name containing`() {
        val marcos = customerRepository.save(buildCustomer(name = "Marcos"))
        val matheus = customerRepository.save(buildCustomer(name = "Matheus"))
        customerRepository.save(buildCustomer(name = "Alex"))

        val customers = customerRepository.findByNameContaining("Ma")

        assertEquals(listOf(marcos, matheus), customers)
    }


    @Nested
    inner class `exists by email` {

        @Test
        fun `should return true when email exists`() {
            val email = "email@email.com"

            customerRepository.save(buildCustomer(email = email))
            val exists = customerRepository.existsByEmail(email)

            Assertions.assertTrue(exists)
        }

        @Test
        fun `should return false when email do not exists`() {
            val email = "noexistingemail@email.com"

            val exists = customerRepository.existsByEmail(email)

            Assertions.assertFalse(exists)
        }

    }

    @Nested
    inner class `find by email` {

        @Test
        fun `should return customer when email find`() {
            val email = "email@email.com"

            val customer = customerRepository.save(buildCustomer(email = email))
            val result = customerRepository.findByEmail(email)

            Assertions.assertNotNull(result)
            Assertions.assertEquals(customer, result)
        }

        @Test
        fun `should return null when email do not find`() {
            val email = "noexistingemail@email.com"

            val exists = customerRepository.findByEmail(email)

            Assertions.assertNull(exists)
        }
    }


    }