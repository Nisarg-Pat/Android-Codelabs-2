package com.example.a024bookshelf.data.network

import com.example.a024bookshelf.model.Book
import com.example.a024bookshelf.model.BookSearchResult
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BookService {
    @GET("volumes")
    suspend fun searchBooks(@Query("q") query: String): BookSearchResult

    @GET("volumes/{volume_id}")
    suspend fun getBookFromID(@Path("volume_id") volumeId: String): Book
}