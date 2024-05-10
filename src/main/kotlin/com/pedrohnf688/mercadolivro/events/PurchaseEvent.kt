package com.pedrohnf688.mercadolivro.events

import com.pedrohnf688.mercadolivro.model.PurchaseModel
import org.springframework.context.ApplicationEvent

class PurchaseEvent(
        source: Any,
        val purchaseModel: PurchaseModel
): ApplicationEvent(source)