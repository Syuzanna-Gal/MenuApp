package com.example.data.remote.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class CategoryEntity(
    @SerialName("idCategory")
    val id: Int,
    @SerialName("strCategory")
    val name: String? = null
)


@Serializable
class CategoriesEntity(
    // NOTE: c letter is Russian, we should take into account that
    @SerialName("categories")
    val categories: List<CategoryEntity>
)