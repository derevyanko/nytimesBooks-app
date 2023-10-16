package com.nytimesbooks.data.local.dataSource

import com.nytimesbooks.data.local.enteties.BooksDbEntity
import com.nytimesbooks.data.local.enteties.CategoryDbEntity

interface BooksLocalDataSource {

    fun getCategories(): List<CategoryDbEntity>?

    fun updateCategories(categories: List<CategoryDbEntity>)

    fun getBooks(list: String): BooksDbEntity?

    fun updateBooks(books: BooksDbEntity)
}