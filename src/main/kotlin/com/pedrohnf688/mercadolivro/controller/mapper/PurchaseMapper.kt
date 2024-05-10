package com.pedrohnf688.mercadolivro.controller.mapper

import com.pedrohnf688.mercadolivro.controller.request.PostPurchaseRequest
import com.pedrohnf688.mercadolivro.enums.BookStatus
import com.pedrohnf688.mercadolivro.model.PurchaseModel
import com.pedrohnf688.mercadolivro.service.BookService
import com.pedrohnf688.mercadolivro.service.CustomerService
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class PurchaseMapper(
        private val bookService: BookService,
        private val customerService: CustomerService
) {

    fun toModel(request: PostPurchaseRequest): PurchaseModel {
        val customer = customerService.findById(request.customerId)
        val books = bookService.finAllByIds(request.bookIds)

        return PurchaseModel(
                customer = customer,
                books = books.toMutableList(),
                price = books.sumOf { it.price }
        )
    }
}