package com.pedrohnf688.mercadolivro.validation

import com.pedrohnf688.mercadolivro.service.CustomerService
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class EmailAvailableValitador(
        private var customerService: CustomerService
): ConstraintValidator<EmailAvailable, String>{

    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        if(value.isNullOrEmpty()) {
            return false
        }

        return customerService.emailAvailable(value)
    }
}