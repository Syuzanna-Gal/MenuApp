package com.example.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class DishUiEntity(
    val id: Int,
    val name: String,
    val pic: String,
    val description: String = "Very nyam nyam meal",
    val price: Int = 345,
) : Parcelable