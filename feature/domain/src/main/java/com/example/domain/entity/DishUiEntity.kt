package com.example.domain.entity

import android.os.Parcelable
import com.example.coreui.util.descriptionList
import kotlinx.parcelize.Parcelize

@Parcelize
class DishUiEntity(
    val id: Int,
    val name: String,
    val pic: String,
    val description: String = descriptionList.random(),
    val price: Int = (300..1200).random(),
) : Parcelable