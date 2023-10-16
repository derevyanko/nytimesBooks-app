package com.nytimesbooks.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nytimesbooks.data.local.enteties.BooksDbEntity
import com.nytimesbooks.data.local.enteties.CategoryDbEntity

@Dao
interface BooksDao {

    @Query("SELECT * FROM categories")
    fun getCategories(): List<CategoryDbEntity>?

    @Insert(entity = CategoryDbEntity::class, onConflict = OnConflictStrategy.REPLACE)
    fun updateRecentPhotos(categories: List<CategoryDbEntity>)

    @Query("SELECT * FROM books WHERE listNameEncoded = :list")
    fun getBooks(list: String): BooksDbEntity?

    @Insert(entity = BooksDbEntity::class, onConflict = OnConflictStrategy.REPLACE)
    fun updateBooks(books: BooksDbEntity)
}