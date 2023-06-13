package com.example.domain.usecase

import com.example.domain.entity.BasketItemUiEntity
import com.example.domain.repository.BasketRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetBasketItemByIdUseCase @Inject constructor(private val repository: BasketRepository) {
     operator fun invoke(id: Int): Flow<BasketItemUiEntity?> =
        repository.getBasketItemById(id).flowOn(Dispatchers.IO)
}