package com.example.data.remote.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class CategoryEntity(
    val id: Int,
    @SerialName("image_url")
    val imageUrl: String? = null,
    val title: String? = null
)


@Serializable
class CategoriesEntity(
    val categories: List<CategoryEntity>
)