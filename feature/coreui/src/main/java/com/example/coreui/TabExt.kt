package com.example.coreui

import android.view.ViewGroup
import android.widget.LinearLayout
import com.google.android.material.tabs.TabLayout

fun TabLayout.changeTabParams() {
    val tabs = this.getChildAt(0) as ViewGroup
    for (i in 0 until tabs.childCount) {
        val tab = tabs.getChildAt(i)
        val layoutParams = tab.layoutParams as LinearLayout.LayoutParams
        layoutParams.weight = 0f
        layoutParams.marginEnd = 8
        layoutParams.marginStart = 8
        layoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT
        tab.layoutParams = layoutParams
        this.requestLayout()
    }
}