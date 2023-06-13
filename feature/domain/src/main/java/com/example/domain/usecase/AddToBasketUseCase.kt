package com.example.domain.usecase

import com.example.domain.entity.DishUiEntity
import com.example.domain.repository.BasketRepository
import javax.inject.Inject

class AddToBasketUseCase @Inject constructor(private val repository: BasketRepository) {
    suspend operator fun invoke(dishUiEntity: DishUiEntity, quantity: Int) =
        repository.addToBasket(dishUiEntity, quantity)
}