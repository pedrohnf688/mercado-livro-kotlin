package com.pedrohnf688.mercadolivro.controller.request

import jakarta.validation.constraints.NotNull
import java.math.BigDecimal

data class PutBookRequest(
        var name: String?,
        var price: BigDecimal?
) {
}