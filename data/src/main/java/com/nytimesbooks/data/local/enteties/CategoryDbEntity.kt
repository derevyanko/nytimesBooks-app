package com.nytimesbooks.data.local.enteties

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class CategoryDbEntity(
    @PrimaryKey val listNameEncoded: String,
    val displayName: String,
    val newestPublishedDate: String,
    val oldestPublishedDate: String,
)
