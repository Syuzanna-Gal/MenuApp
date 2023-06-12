package com.example.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class DishUi(
    val id: Int,
    val name: String,
    val price: Int,
    val weight: Int,
    val description: String,
    val imageUrl: String,
    val tags: List<String>
) : Parcelable