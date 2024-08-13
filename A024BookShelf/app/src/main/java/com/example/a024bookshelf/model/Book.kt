package com.example.a024bookshelf.model

import kotlinx.serialization.Serializable

@Serializable
data class Book(
    val id: String,
    val volumeInfo: VolumeInfo
)

@Serializable
data class VolumeInfo(
    val imageLinks: ImageLink
)

@Serializable
data class BookSearchResult(
    val items: List<BookItem>
)

@Serializable
data class BookItem(
    val id: String
)

@Serializable
data class ImageLink(
    val thumbnail: String
)
