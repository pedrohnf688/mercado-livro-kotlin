package com.pedrohnf688.mercadolivro.repsitory

import com.pedrohnf688.mercadolivro.model.PurchaseModel
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PurchaseRepository : CrudRepository<PurchaseModel, Int>{
}