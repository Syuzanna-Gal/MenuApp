package com.example.data.local.db

import androidx.room.*
import com.example.data.local.db.entity.BasketItemDb
import kotlinx.coroutines.flow.Flow

@Dao
interface BasketItemDao {

    @Query("SELECT * FROM basket_item")
    suspend fun findAll(): List<BasketItemDb>

    @Query("SELECT * FROM basket_item")
    fun findAllAsFlow(): Flow<List<BasketItemDb>>

    @Query("SELECT SUM(quantity) FROM basket_item")
    fun sumOfQuantityAsFlow(): Flow<Int>

    @Query("SELECT * FROM basket_item WHERE id = :productId")
    suspend fun findById(productId: Int): BasketItemDb

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cartItem: BasketItemDb)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(cartItem: BasketItemDb)

    @Delete
    suspend fun delete(cartItem: BasketItemDb)
}