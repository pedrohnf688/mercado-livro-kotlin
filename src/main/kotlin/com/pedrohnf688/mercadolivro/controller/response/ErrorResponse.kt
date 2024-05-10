package com.pedrohnf688.mercadolivro.controller.response

data class ErrorResponse(
        var httpCode: Int,
        var message: String,
        var internalCode: String,
        var errors: List<FieldErrorResponse>?
)