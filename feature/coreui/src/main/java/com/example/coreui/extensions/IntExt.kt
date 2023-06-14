package com.example.coreui.extensions

import android.content.res.Resources

val Int.px: Int
    get() = (this / Resources.getSystem().displayMetrics.density).toInt()

val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

fun Int.toSpacedFormat(): String {
    val reversedNumber = StringBuilder(this.toString().reversed())
    reversedNumber.forEachIndexed { index, _ ->
        if (index % 4 == 0) reversedNumber.insert(index, " ")
    }
    return reversedNumber.insert(reversedNumber.lastIndex + 1,"").reversed().toString()
}