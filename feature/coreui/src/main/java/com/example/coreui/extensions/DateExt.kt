package com.example.coreui.extensions

import com.example.coreui.util.DATE_FORMAT
import com.example.coreui.util.DATE_LOCALE_TYPE
import java.text.SimpleDateFormat
import java.util.*

fun getCurrentDate(): String {
    val time = Calendar.getInstance().time
    val formatter = SimpleDateFormat(DATE_FORMAT, DATE_LOCALE_TYPE)
    return formatter.format(time)
}