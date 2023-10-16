package com.nytimesbooks.data.remote.dto.books

import com.google.gson.annotations.SerializedName

data class BooksDto(
    @SerializedName("num_results")
    val number: Int,

    @SerializedName("results")
    val books: BooksCategoryInfoDto
)