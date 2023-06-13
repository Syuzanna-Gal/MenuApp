package com.example.data.remote.entity

import kotlinx.serialization.SerialName

class DishEntity(
    val id: Int,
    val name: String? = null,
    val price: Double,
    val weight: Double,
    val description: String? = null,
    @SerialName("image_url")
    val imageUrl: String?=null,
    @SerialName("tegs")
    val tags: List<String> = listOf()
)

class DishesEntity(
    val dishes: List<DishEntity>
)