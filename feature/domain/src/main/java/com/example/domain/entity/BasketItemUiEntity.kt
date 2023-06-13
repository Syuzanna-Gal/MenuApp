package com.example.domain.entity

class BasketItemUiEntity(
    val id: Int,
    val name: String,
    val price: Int,
    val weight: Int,
    val imageUrl: String,
    val amount: Int = 0
)