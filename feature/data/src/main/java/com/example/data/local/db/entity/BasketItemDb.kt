package com.example.data.local.db.entity


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "basket_item", [Index(value = ["created_at"])])
data class BasketItemDb(
    @PrimaryKey
    val id: Int,
    val name: String,
    val price: Double,
    val weight: Double,
    @ColumnInfo(name = "image_url")
    val imageUrl: String,
    // note: mutable fields
    @ColumnInfo(name = "created_at")
    var createdAt: Long,
    var quantity: Int = 0,
)