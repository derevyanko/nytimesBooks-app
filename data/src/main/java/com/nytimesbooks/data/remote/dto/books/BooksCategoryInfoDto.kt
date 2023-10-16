package com.nytimesbooks.data.remote.dto.books

import com.google.gson.annotations.SerializedName

data class BooksCategoryInfoDto(
    @SerializedName("list_name_encoded")
    val listNameEncoded: String,

    @SerializedName("display_name")
    val listNameDisplay: String,

    val books: List<BookDto>
)