package com.example.domain.entity

data class BasketItemUiEntity(
    val id: Int,
    val name: String,
    val price: Double,
    val weight: Double,
    val imageUrl: String,
    val quantity: Int = 0
)