package com.pedrohnf688.mercadolivro.events.listener

import com.pedrohnf688.mercadolivro.events.PurchaseEvent
import com.pedrohnf688.mercadolivro.service.BookService
import com.pedrohnf688.mercadolivro.service.PurchaseService
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class UpdateSoldBookListerner(
        private val bookService: BookService
) {

    @Async
    @EventListener
    fun listen(purchaseEvent: PurchaseEvent) {
        bookService.purchase(purchaseEvent.purchaseModel.books)
    }
}