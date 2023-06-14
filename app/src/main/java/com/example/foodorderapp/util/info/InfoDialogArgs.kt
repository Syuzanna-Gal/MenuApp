package com.example.foodorderapp.util.info

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class InfoDialogArgs(
    val title: String,
    val message: String,
    val buttonText: String
) : Parcelable