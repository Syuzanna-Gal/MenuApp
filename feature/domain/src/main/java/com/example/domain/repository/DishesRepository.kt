package com.example.domain.repository

import com.example.domain.entity.DishUiEntity
import kotlinx.coroutines.flow.Flow

interface DishesRepository {
    fun fetchDishes(category: String): Flow<List<DishUiEntity>>
}