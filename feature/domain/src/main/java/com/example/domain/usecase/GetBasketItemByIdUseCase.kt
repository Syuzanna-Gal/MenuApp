package com.example.domain.usecase

import com.example.domain.entity.BasketItemUiEntity
import com.example.domain.repository.BasketRepository
import javax.inject.Inject

class GetBasketItemByIdUseCase @Inject constructor(private val repository: BasketRepository) {
     operator fun invoke(id: Int): BasketItemUiEntity? =
        repository.getBasketItemById(id)
}