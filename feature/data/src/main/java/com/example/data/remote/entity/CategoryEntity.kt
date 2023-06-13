package com.example.data.remote.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class CategoryEntity(
    val id: Int,
    @SerialName("image_url")
    val imageUrl: String? = null,
    val name: String? = null
)


@Serializable
class CategoriesEntity(
    // NOTE: c letter is Russian, we should tak into account that
    @SerialName("сategories")
    val categories: List<CategoryEntity>
)