package com.pedrohnf688.mercadolivro.service

import com.pedrohnf688.mercadolivro.events.PurchaseEvent
import com.pedrohnf688.mercadolivro.model.PurchaseModel
import com.pedrohnf688.mercadolivro.repsitory.PurchaseRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service


@Service
class PurchaseService(
        private val purchaseRepository: PurchaseRepository,
        private val applicationEventPublisher: ApplicationEventPublisher
) {

    fun create(purchaseModel: PurchaseModel) {
        purchaseRepository.save(purchaseModel)

        applicationEventPublisher.publishEvent(PurchaseEvent(this, purchaseModel))
    }

    fun update(purchaseModel: PurchaseModel) {
        purchaseRepository.save(purchaseModel)
    }
}