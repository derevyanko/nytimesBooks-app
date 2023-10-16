package com.nytimesbooks.data.remote.api

import com.nytimesbooks.data.remote.dto.books.BooksDto
import com.nytimesbooks.data.remote.dto.categories.CategoriesDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BooksApiService {

    @GET("svc/books/v3/lists/names.json")
    suspend fun getCategories(): Response<CategoriesDto>

    @GET("svc/books/v3/lists/current/{list}")
    suspend fun getBooksByCategory(@Path("list") list: String): Response<BooksDto>
}