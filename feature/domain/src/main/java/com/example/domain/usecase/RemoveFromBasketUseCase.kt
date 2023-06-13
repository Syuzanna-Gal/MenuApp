package com.example.domain.usecase

import com.example.domain.entity.BasketItemUiEntity
import com.example.domain.repository.BasketRepository
import javax.inject.Inject

class RemoveFromBasketUseCase @Inject constructor(private val repository: BasketRepository) {
    suspend operator fun invoke(basketItemUiEntity: BasketItemUiEntity) {
        val quantity = basketItemUiEntity.quantity - 1
        if (quantity != 0)
            repository.updateBasketItem(basketItemUiEntity.copy(quantity = quantity))
        else
            repository.deleteFromBasket(basketItemUiEntity)
    }
}