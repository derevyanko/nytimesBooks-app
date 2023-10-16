package com.nytimesbooks.domain.models.categories

data class Category(
    val listNameEncoded: String,
    val displayName: String,
    val newestPublishedDate: String,
    val oldestPublishedDate: String,
)
