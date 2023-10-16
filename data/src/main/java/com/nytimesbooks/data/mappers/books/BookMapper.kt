package com.nytimesbooks.data.mappers.books

import com.nytimesbooks.data.local.enteties.BooksDbEntity
import com.nytimesbooks.data.remote.dto.books.BookDto
import com.nytimesbooks.domain.models.books.Book

object BookMapper {

    fun fromDtoToDomain(from: List<BookDto>) = from.map {
        fromDtoToDomain(it)
    }

    private fun fromDtoToDomain(from: BookDto) = Book(
        urlAmazon = from.urlAmazon,
        rank = from.rank,
        isbn = from.isbn,
        urlImage = from.urlImage,
        title = from.title,
        description = from.description,
        author = from.author,
        publisher = from.publisher
    )

    fun fromDtoToDbEntity(from: List<BookDto>) = from.map {
        fromDtoToDb(it)
    }

    private fun fromDtoToDb(from: BookDto) = BooksDbEntity.BookDb(
        urlAmazon = from.urlAmazon,
        rank = from.rank,
        isbn = from.isbn,
        urlImage = from.urlImage,
        title = from.title,
        description = from.description,
        author = from.author,
        publisher = from.publisher
    )


    fun fromDbToDomain(from: List<BooksDbEntity.BookDb>) = from.map {
        fromDbToDomain(it)
    }

    private fun fromDbToDomain(from: BooksDbEntity.BookDb) = Book(
        urlAmazon = from.urlAmazon,
        rank = from.rank,
        isbn = from.isbn,
        urlImage = from.urlImage,
        title = from.title,
        description = from.description,
        author = from.author,
        publisher = from.publisher
    )
}