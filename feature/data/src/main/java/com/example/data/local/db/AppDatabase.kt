package com.example.data.local.db

import com.example.data.local.db.entity.BasketItemDb
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [BasketItemDb::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun basketItemDao(): BasketItemDao
}