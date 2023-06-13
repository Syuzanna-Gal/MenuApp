package com.example.data.remote.entity

import kotlinx.serialization.SerialName

class CategoryEntity(
    val id: Int,
    @SerialName("image_url")
    val imageUrl: String? = null,
    val title: String? = null
)

class CategoriesEntity(
    val сategories: List<CategoryEntity>
)