package com.example.data.remote.api

import retrofit2.http.GET

interface MainApi {

    @GET("058729bd-1402-4578-88de-265481fd7d54")
    suspend fun fetchCategories()

    @GET("058729bd-1402-4578-88de-265481fd7d54")
    suspend fun fetchDishes()
}