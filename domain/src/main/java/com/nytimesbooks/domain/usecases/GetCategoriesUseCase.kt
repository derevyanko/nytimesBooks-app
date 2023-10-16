package com.nytimesbooks.domain.usecases

import com.nytimesbooks.domain.repository.BooksRepository
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor (
    private val booksRepository: BooksRepository
) {

    suspend operator fun invoke() = booksRepository.getCategories()
}