package com.example.data.remote.api

import com.example.data.remote.entity.CategoriesEntity
import com.example.data.remote.entity.DishesEntity
import retrofit2.http.GET

interface MainApi {

    @GET("058729bd-1402-4578-88de-265481fd7d54")
    suspend fun fetchCategories(): List<CategoriesEntity>

    @GET("aba7ecaa-0a70-453b-b62d-0e326c859b3b")
    suspend fun fetchDishes(): List<DishesEntity>
}