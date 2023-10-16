package com.nytimesbooks.data.local.dataSource

import com.nytimesbooks.data.local.dao.BooksDao
import com.nytimesbooks.data.local.enteties.BooksDbEntity
import com.nytimesbooks.data.local.enteties.CategoryDbEntity
import javax.inject.Inject

class BooksLocalDataSourceImpl @Inject constructor(
    private val dao: BooksDao,
): BooksLocalDataSource {

    override fun getCategories() = dao.getCategories()

    override fun updateCategories(categories: List<CategoryDbEntity>) = dao.updateRecentPhotos(categories)

    override fun getBooks(list: String): BooksDbEntity? = dao.getBooks(list)

    override fun updateBooks(books: BooksDbEntity) = dao.updateBooks(books)
}