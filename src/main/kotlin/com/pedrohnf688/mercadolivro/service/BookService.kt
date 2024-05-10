package com.pedrohnf688.mercadolivro.service

import com.pedrohnf688.mercadolivro.enums.BookStatus
import com.pedrohnf688.mercadolivro.enums.Errors
import com.pedrohnf688.mercadolivro.exceptions.NotFoundException
import com.pedrohnf688.mercadolivro.model.BookModel
import com.pedrohnf688.mercadolivro.model.CustomerModel
import com.pedrohnf688.mercadolivro.repsitory.BookRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.awt.print.Book

@Service
class BookService(
        private val bookRepository: BookRepository
) {


    fun create(book: BookModel) {
        bookRepository.save(book);
    }

    fun findAll(pageable: Pageable): Page<BookModel> {
        return bookRepository.findAll(pageable)
    }

    fun findActives(pageable: Pageable): Page<BookModel> {
        return bookRepository.findByStatus(BookStatus.ATIVO, pageable)
    }

    fun findById(id: Int): BookModel {
        return bookRepository.findById(id).orElseThrow{ NotFoundException(Errors.ML0001.message.format(id), Errors.ML0001.code) }
    }

    fun delete(id: Int) {
        val book = findById(id)
        book.status = BookStatus.CANCELADO

        update(book)
    }

    fun update(book: BookModel) {
        bookRepository.save(book)
    }

    fun deleteByCustomer(customer: CustomerModel) {
        val books = bookRepository.findByCustomer(customer)

        for(book in books) {
            book.status = BookStatus.DELETADO
        }

        bookRepository.saveAll(books)
    }

    fun finAllByIds(bookIds: Set<Int>): List<BookModel> {
        return bookRepository.findAllById(bookIds)
                .filter { it.status == BookStatus.ATIVO }
                .toList()
    }

    fun purchase(books: MutableList<BookModel>) {
        books.map {
            it.status = BookStatus.VENDIDO
        }

        bookRepository.saveAll(books)
    }

}