package com.nytimesbooks.data.mappers.categories

import com.nytimesbooks.data.local.enteties.CategoryDbEntity
import com.nytimesbooks.data.remote.dto.categories.CategoriesDto
import com.nytimesbooks.domain.models.categories.Categories

object CategoriesMapper {

    fun fromDtoToDomain(from: CategoriesDto): Categories =
        Categories(
            numberResults = from.numberResults,
            categories = CategoryMapper.fromDtoToDomain(from.categories),
        )

    fun fromDtoToDbEntity(from: CategoriesDto): List<CategoryDbEntity> =
        CategoryMapper.fromDtoToDbEntity(from.categories)


    fun fromDbEntityToDomain(from: List<CategoryDbEntity>): Categories = Categories(
        numberResults = from.size,
        categories = CategoryMapper.fromDbEntityToDomain(from)
    )
}