package com.example.a024bookshelf.data.repository

import com.example.a024bookshelf.data.network.BookService
import com.example.a024bookshelf.model.Book
import com.example.a024bookshelf.model.BookItem

interface BookRepository {
    suspend fun searchBooks(query: String): List<BookItem>

    suspend fun getBookFromID(volumeId: String): Book
}

class NetworkBookRepository(private val bookService: BookService): BookRepository {
    override suspend fun searchBooks(query: String): List<BookItem> {
        return bookService.searchBooks(query).items
    }

    override suspend fun getBookFromID(volumeId: String): Book {
        return bookService.getBookFromID(volumeId)
    }

}