package com.nytimesbooks.data.remote.dataSource

import com.nytimesbooks.data.remote.base.NetworkState
import com.nytimesbooks.data.remote.dto.books.BooksDto
import com.nytimesbooks.data.remote.dto.categories.CategoriesDto

interface BooksRemoteDataSource {

    suspend fun getCategories(): NetworkState<CategoriesDto>

    suspend fun getBooks(list: String): NetworkState<BooksDto>
}