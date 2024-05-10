package com.pedrohnf688.mercadolivro.controller

import com.pedrohnf688.mercadolivro.controller.mapper.PurchaseMapper
import com.pedrohnf688.mercadolivro.controller.request.PostPurchaseRequest
import com.pedrohnf688.mercadolivro.service.PurchaseService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("purchase")
class PurchaseController(
        private val purchaseService: PurchaseService,
        private val purchaseMapper: PurchaseMapper
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun purchase(@RequestBody request: PostPurchaseRequest) {
        purchaseService.create(purchaseMapper.toModel(request))
    }

}