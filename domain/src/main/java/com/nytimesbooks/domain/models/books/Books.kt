package com.nytimesbooks.domain.models.books

data class Books(
    val books: List<Book>,
    val listNameEncoded: String,
    val listNameDisplay: String
)