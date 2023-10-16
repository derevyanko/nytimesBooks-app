package com.nytimesbooks.domain.repository

import com.nytimesbooks.domain.base.UIState
import com.nytimesbooks.domain.models.books.Books
import com.nytimesbooks.domain.models.categories.Categories
import kotlinx.coroutines.flow.Flow

interface BooksRepository {

    suspend fun getCategories(): Flow<UIState<Categories>>

    suspend fun getBooks(list: String): Flow<UIState<Books>>
}