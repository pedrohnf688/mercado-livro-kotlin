package com.pedrohnf688.mercadolivro.model

import com.pedrohnf688.mercadolivro.enums.CustomerStatus
import com.pedrohnf688.mercadolivro.enums.Role
import jakarta.persistence.*

@Entity(name = "customer")
data class CustomerModel(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int? = null,

        @Column
        var name: String,

        @Column
        var email: String,

        @Column
        @Enumerated(EnumType.STRING)
        var status: CustomerStatus,

        @Column
        val password: String,

        @Column(name = "role")
        @Enumerated(EnumType.STRING)
        @ElementCollection(targetClass = Role::class, fetch = FetchType.EAGER)
        @CollectionTable(name = "customer_roles", joinColumns = [JoinColumn(name = "customer_id")])
        var roles: Set<Role> = setOf()
)