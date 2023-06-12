package com.example.domain.models

class BasketItemUi(
    val id: Int,
    val name: String,
    val price: Int,
    val weight: Int,
    val imageUrl: String,
    val amount: Int = 0
)