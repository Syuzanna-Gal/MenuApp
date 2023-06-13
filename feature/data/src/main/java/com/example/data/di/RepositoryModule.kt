package com.example.data.di


import com.example.data.repository.BasketRepositoryImpl
import com.example.data.repository.CategoryRepositoryImpl
import com.example.data.repository.DishesRepositoryImpl
import com.example.domain.repository.BasketRepository
import com.example.domain.repository.CategoryRepository
import com.example.domain.repository.DishesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindCategoryRepository(impl: CategoryRepositoryImpl): CategoryRepository

    @Binds
    fun bindDishesRepository(impl: DishesRepositoryImpl): DishesRepository

    @Binds
    fun bindBasketRepository(impl: BasketRepositoryImpl): BasketRepository
}