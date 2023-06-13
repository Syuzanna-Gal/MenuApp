package com.example.data.local.db

import androidx.room.*
import com.example.data.local.db.entity.BasketItemDbEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BasketItemDao {

    @Query("SELECT * FROM basket_item")
    suspend fun findAll(): List<BasketItemDbEntity>

    @Query("SELECT * FROM basket_item")
    fun findAllAsFlow(): Flow<List<BasketItemDbEntity>>

    @Query("SELECT SUM(quantity) FROM basket_item")
    fun sumOfQuantityAsFlow(): Flow<Int>

    @Query("SELECT * FROM basket_item WHERE id = :productId")
    suspend fun findById(productId: Int): BasketItemDbEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cartItem: BasketItemDbEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(cartItem: BasketItemDbEntity)

    @Delete
    suspend fun delete(cartItem: BasketItemDbEntity)
}