package com.nytimesbooks.domain.models.books

data class Book(
    val urlAmazon: String,
    val urlImage: String,
    val isbn: String,
    val title: String,
    val description: String,
    val author: String,
    val publisher: String,
    val rank: Int
)
