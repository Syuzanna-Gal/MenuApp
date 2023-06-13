package com.example.data.local.db

import androidx.room.*
import com.example.data.local.db.entity.BasketItemDbEntity
import dagger.Provides
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

@Dao
interface BasketItemDao {

    @Query("SELECT * FROM basket_item")
    suspend fun findAll(): List<BasketItemDbEntity>

    @Query("SELECT * FROM basket_item")
    fun findAllAsFlow(): Flow<List<BasketItemDbEntity>>

    @Query("SELECT SUM(quantity) FROM basket_item")
    fun sumOfQuantityAsFlow(): Flow<Int>

    @Query("SELECT * FROM basket_item WHERE id = :productId")
    fun findById(productId: Int): BasketItemDbEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(basketItem: BasketItemDbEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(basketItem: BasketItemDbEntity)

    @Delete
    suspend fun delete(basketItem: BasketItemDbEntity)
}