package com.example.domain.usecase

import com.example.domain.repository.BasketRepository
import javax.inject.Inject

class RemoveAllUseCase @Inject constructor(private val repository: BasketRepository) {
    suspend operator fun invoke() = repository.deleteAll()
}