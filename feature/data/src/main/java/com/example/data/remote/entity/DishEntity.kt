package com.example.data.remote.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
class DishEntity(
    @SerialName("idMeal")
    val id: Int,
    @SerialName("strMealThumb")
    val pic: String? = null,
    @SerialName("strMeal")
    val name: String? = null
)

@Serializable
class DishesEntity(
    @SerialName("meals")
    val dishes: List<DishEntity>
)