package com.nytimesbooks.data.remote.dto.categories

import com.google.gson.annotations.SerializedName

data class CategoriesDto(
    @SerializedName("num_results")
    val numberResults: Int,

    @SerializedName("results")
    val categories: List<CategoryDto>
)