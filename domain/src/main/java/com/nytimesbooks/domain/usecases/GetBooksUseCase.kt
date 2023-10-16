package com.nytimesbooks.domain.usecases

import com.nytimesbooks.domain.repository.BooksRepository
import javax.inject.Inject

class GetBooksUseCase @Inject constructor(
    private val booksRepository: BooksRepository
) {

    suspend operator fun invoke(list: String) = booksRepository.getBooks(list)
}