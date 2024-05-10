package com.pedrohnf688.mercadolivro.validation

import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Constraint(validatedBy = [EmailAvailableValitador::class])
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
annotation class EmailAvailable(
        val message: String = "Email já cadastrado",
        val groups: Array<KClass<*>> = [],
        val payload: Array<KClass<out Payload>> = []
)
