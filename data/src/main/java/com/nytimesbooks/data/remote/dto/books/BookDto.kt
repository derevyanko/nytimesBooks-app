package com.nytimesbooks.data.remote.dto.books

import com.google.gson.annotations.SerializedName

data class BookDto(
    @SerializedName("amazon_product_url")
    val urlAmazon: String,

    @SerializedName("book_image")
    val urlImage: String,

    @SerializedName("primary_isbn13")
    val isbn: String,

    val title: String,
    val description: String,
    val author: String,
    val publisher: String,
    val rank: Int
)