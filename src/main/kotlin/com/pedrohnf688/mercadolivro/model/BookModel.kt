package com.pedrohnf688.mercadolivro.model

import com.pedrohnf688.mercadolivro.enums.BookStatus
import com.pedrohnf688.mercadolivro.enums.Errors
import com.pedrohnf688.mercadolivro.exceptions.BadRequestException
import jakarta.persistence.*
import java.math.BigDecimal

@Entity(name = "book")
data class BookModel(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int? = null,

        @Column
        var name: String,

        @Column
        var price: BigDecimal,

        @ManyToOne
        @JoinColumn(name = "customer_id")
        var customer: CustomerModel? = null
) {

    @Column
    @Enumerated(EnumType.STRING)
    var status: BookStatus? = null
        set(value) {
            if(field == BookStatus.CANCELADO || field == BookStatus.DELETADO){
                throw BadRequestException(Errors.ML0003.message.format(field), Errors.ML0003.code)
            }

            field = value
        }

    constructor(id: Int? = null,
                name: String,
                price: BigDecimal,
                customer: CustomerModel? = null,
                status: BookStatus?): this(id, name, price, customer) {
                    this.status = status
            }


}














