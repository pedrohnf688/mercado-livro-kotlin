package com.pedrohnf688.mercadolivro.repsitory

import com.pedrohnf688.mercadolivro.enums.BookStatus
import com.pedrohnf688.mercadolivro.model.BookModel
import com.pedrohnf688.mercadolivro.model.CustomerModel
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface BookRepository : JpaRepository<BookModel, Int> {

    fun findByStatus(status: BookStatus, pageable: Pageable): Page<BookModel>

    fun findByCustomer(customer: CustomerModel): List<BookModel>

    //fun findAll(pageable: Pageable): Page<BookModel>

}