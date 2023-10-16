package com.nytimesbooks.data.mappers.categories

import com.nytimesbooks.data.local.enteties.CategoryDbEntity
import com.nytimesbooks.data.remote.dto.categories.CategoryDto
import com.nytimesbooks.domain.models.categories.Category

object CategoryMapper {

    fun fromDtoToDomain(from: List<CategoryDto>) = from.map {
        fromDtoToDomain(it)
    }

    private fun fromDtoToDomain(from: CategoryDto): Category =
        Category(
            listNameEncoded = from.listNameEncoded,
            displayName = from.displayName,
            newestPublishedDate = from.newestPublishedDate,
            oldestPublishedDate = from.oldestPublishedDate
        )


    fun fromDtoToDbEntity(from: List<CategoryDto>) = from.map {
        fromDtoToDbEntity(it)
    }

    private fun fromDtoToDbEntity(from: CategoryDto): CategoryDbEntity =
        CategoryDbEntity(
            listNameEncoded = from.listNameEncoded,
            displayName = from.displayName,
            newestPublishedDate = from.newestPublishedDate,
            oldestPublishedDate = from.oldestPublishedDate
        )


    fun fromDbEntityToDomain(from: List<CategoryDbEntity>) = from.map {
        fromDbEntityToDomain(it)
    }

    private fun fromDbEntityToDomain(from: CategoryDbEntity): Category =
        Category(
            listNameEncoded = from.listNameEncoded,
            displayName = from.displayName,
            newestPublishedDate = from.newestPublishedDate,
            oldestPublishedDate = from.oldestPublishedDate
        )
}