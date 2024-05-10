package com.pedrohnf688.mercadolivro.controller.response

import com.pedrohnf688.mercadolivro.enums.BookStatus
import com.pedrohnf688.mercadolivro.model.CustomerModel
import java.math.BigDecimal

data class BookResponse(
        var id: Int? = null,
        var name: String,
        var price: BigDecimal,
        var customer: CustomerModel? = null,
        var status: BookStatus? = null
)