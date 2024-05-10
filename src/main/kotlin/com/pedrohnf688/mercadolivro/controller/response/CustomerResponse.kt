package com.pedrohnf688.mercadolivro.controller.response

import com.pedrohnf688.mercadolivro.enums.CustomerStatus

data class CustomerResponse(
        var id: Int? = null,
        var name: String,
        var email: String,
        var status: CustomerStatus
)