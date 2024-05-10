package com.pedrohnf688.mercadolivro.controller.request

import com.pedrohnf688.mercadolivro.validation.EmailAvailable
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty

data class PostCustomerRequest(

        @field:NotEmpty(message = "Nome deve ser informado")
        var name: String,

        @field:Email(message = "Email deve ser válido")
        @EmailAvailable
        var email: String,

        @field:NotEmpty(message = "Senha deve ser informada")
        var password: String
)