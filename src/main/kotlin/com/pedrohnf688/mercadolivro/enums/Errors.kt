package com.pedrohnf688.mercadolivro.enums

enum class Errors(
        val code: String,
        val message: String
) {

    ML0001("ML-0001", "Book [%s] not exists"),
    MlOOO2("ML-0002", "Customer [%s] not exists"),
    ML0003("ML-0003", "Cannot update book with status [%s]"),
    ML0004("ML-0004","Invalid Request")



}