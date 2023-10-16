package com.nytimesbooks.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nytimesbooks.data.local.converters.BookDbTypeConverter
import com.nytimesbooks.data.local.dao.BooksDao
import com.nytimesbooks.data.local.enteties.BooksDbEntity
import com.nytimesbooks.data.local.enteties.CategoryDbEntity

@Database(
    version = 1,
    entities = [
        CategoryDbEntity::class,
        BooksDbEntity::class
    ]
)
@TypeConverters(BookDbTypeConverter::class)
abstract class BooksDatabase: RoomDatabase() {

    abstract fun getBooksDao(): BooksDao
}