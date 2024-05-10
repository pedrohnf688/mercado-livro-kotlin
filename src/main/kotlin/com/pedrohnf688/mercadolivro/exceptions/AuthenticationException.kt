package com.pedrohnf688.mercadolivro.exceptions

class AuthenticationException(
        override val message: String,
        val errorCode: String
): Exception() {
}