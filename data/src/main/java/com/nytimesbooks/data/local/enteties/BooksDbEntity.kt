package com.nytimesbooks.data.local.enteties

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class BooksDbEntity(
    @PrimaryKey val listNameEncoded: String,
    val listNameDisplay: String,
    val books: List<BookDb>,
) {

    data class BookDb(
        val urlAmazon: String,
        val urlImage: String,
        val isbn: String,
        val title: String,
        val description: String,
        val author: String,
        val publisher: String,
        val rank: Int
    )
}