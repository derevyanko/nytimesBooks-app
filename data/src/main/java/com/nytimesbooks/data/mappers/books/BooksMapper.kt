package com.nytimesbooks.data.mappers.books

import com.nytimesbooks.data.local.enteties.BooksDbEntity
import com.nytimesbooks.data.remote.dto.books.BooksDto
import com.nytimesbooks.domain.models.books.Books

object BooksMapper {

    fun fromDtoToDomain(from: BooksDto) = Books(
        books = BookMapper.fromDtoToDomain(from.books.books),
        listNameEncoded = from.books.listNameEncoded,
        listNameDisplay = from.books.listNameDisplay
    )

    fun fromDtoToDbEntity(from: BooksDto) = BooksDbEntity(
        listNameEncoded = from.books.listNameEncoded,
        listNameDisplay = from.books.listNameDisplay,
        books = BookMapper.fromDtoToDbEntity(from.books.books)
    )


    fun fromDbEntityToDomain(from: BooksDbEntity) = Books(
        books = BookMapper.fromDbToDomain(from.books),
        listNameEncoded = from.listNameEncoded,
        listNameDisplay = from.listNameDisplay
    )
}