package com.pedrohnf688.mercadolivro.extension

import com.pedrohnf688.mercadolivro.controller.request.PostBookRequest
import com.pedrohnf688.mercadolivro.controller.request.PostCustomerRequest
import com.pedrohnf688.mercadolivro.controller.request.PutBookRequest
import com.pedrohnf688.mercadolivro.controller.request.PutCustomerRequest
import com.pedrohnf688.mercadolivro.controller.response.BookResponse
import com.pedrohnf688.mercadolivro.controller.response.CustomerResponse
import com.pedrohnf688.mercadolivro.controller.response.PageResponse
import com.pedrohnf688.mercadolivro.enums.BookStatus
import com.pedrohnf688.mercadolivro.enums.CustomerStatus
import com.pedrohnf688.mercadolivro.model.BookModel
import com.pedrohnf688.mercadolivro.model.CustomerModel
import org.springframework.data.domain.Page

fun PostCustomerRequest.toCustomerModel(): CustomerModel {
    return CustomerModel(
            name = this.name,
            email = this.email,
            status = CustomerStatus.ATIVO,
            password = this.password
    )
}

fun PutCustomerRequest.toCustomerModel(previousValue: CustomerModel): CustomerModel {
    return CustomerModel(
            id = previousValue.id,
            name = previousValue.name,
            email = previousValue.email,
            status = previousValue.status,
            password = previousValue.password
    )
}

fun PostBookRequest.toBookModel(customer: CustomerModel): BookModel {
    return BookModel(
            name = this.name,
            price = this.price,
            status = BookStatus.ATIVO,
            customer = customer
    )
}

fun PutBookRequest.toBookModel(previousValue: BookModel): BookModel {
    return BookModel(
            id = previousValue.id,
            name = this.name ?: previousValue.name,
            price = this.price ?: previousValue.price,
            status = previousValue.status,
            customer = previousValue.customer
        )

}

fun CustomerModel.toResponse(): CustomerResponse {
    return CustomerResponse(
            id = this.id,
            name = this.name,
            email = this.email,
            status = this.status
    )
}


fun BookModel.toResponse(): BookResponse {
    return BookResponse(
            id = this.id,
            name = this.name,
            price = this.price,
            customer = this.customer,
            status = this.status
    )
}


fun <T> Page<T>.toPageResponse(): PageResponse<T> {
    return  PageResponse(
            this.content,
            this.number,
            this.totalElements,
            this.totalPages
    )
}





