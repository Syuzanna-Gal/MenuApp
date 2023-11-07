package com.example.data.remote.api

import com.example.data.remote.entity.CategoriesEntity
import com.example.data.remote.entity.DishesEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface MainApi {

    @GET("1/categories.php")
    suspend fun fetchCategories(): CategoriesEntity

    @GET("1/filter.php")
    suspend fun fetchDishes(
        @Query("c") category: String,
    ): DishesEntity
}