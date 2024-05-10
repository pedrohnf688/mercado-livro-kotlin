package com.pedrohnf688.mercadolivro.controller.response

class PageResponse<T>(
        var items: List<T>,
        var currentPage: Int,
        var totalItems: Long,
        var totalPage: Int
)