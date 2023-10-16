package com.nytimesbooks.data.local.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nytimesbooks.data.local.enteties.BooksDbEntity

object BookDbTypeConverter {

    private val gson = Gson()

    @TypeConverter
    @JvmStatic
    fun toString(books: List<BooksDbEntity.BookDb>): String {
        return gson.toJson(books)
    }

    @TypeConverter
    @JvmStatic
    fun fromString(data: String): List<BooksDbEntity.BookDb> {
        val listType = object : TypeToken<List<BooksDbEntity.BookDb>>() {}.type
        return gson.fromJson(data, listType)
    }
}