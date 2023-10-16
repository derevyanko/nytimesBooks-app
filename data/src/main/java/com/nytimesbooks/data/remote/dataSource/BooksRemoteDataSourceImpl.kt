package com.nytimesbooks.data.remote.dataSource

import com.nytimesbooks.data.remote.api.BooksApiService
import com.nytimesbooks.data.remote.base.BaseRepository
import javax.inject.Inject

class BooksRemoteDataSourceImpl @Inject constructor(
    private val api: BooksApiService
): BaseRepository(), BooksRemoteDataSource {

    override suspend fun getCategories() = request {
        api.getCategories()
    }

    override suspend fun getBooks(list: String) = request {
        api.getBooksByCategory(list)
    }
}