package com.example.nytimesbooks.di.domain

import com.nytimesbooks.domain.repository.BooksRepository
import com.nytimesbooks.domain.usecases.GetBooksUseCase
import com.nytimesbooks.domain.usecases.GetCategoriesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object DomainModule {

    @Provides
    fun providesGetCategoriesUseCase(booksRepository: BooksRepository): GetCategoriesUseCase =
        GetCategoriesUseCase(booksRepository = booksRepository)

    @Provides
    fun providesGetBooksUseCase(booksRepository: BooksRepository): GetBooksUseCase =
        GetBooksUseCase(booksRepository = booksRepository)
}