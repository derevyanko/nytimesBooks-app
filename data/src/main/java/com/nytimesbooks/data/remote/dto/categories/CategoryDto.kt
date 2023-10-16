package com.nytimesbooks.data.remote.dto.categories

import com.google.gson.annotations.SerializedName

data class CategoryDto(
    @SerializedName("list_name_encoded")
    val listNameEncoded: String,

    @SerializedName("display_name")
    val displayName: String,

    @SerializedName("newest_published_date")
    val newestPublishedDate: String,

    @SerializedName("oldest_published_date")
    val oldestPublishedDate: String
)