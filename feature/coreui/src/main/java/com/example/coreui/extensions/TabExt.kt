package com.example.coreui.extensions

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

fun TabLayout.addOnTabSelectedListener(
    onTabSelected: (TabLayout.Tab?) -> Unit
) {
    addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
        override fun onTabSelected(tab: TabLayout.Tab?) {
            onTabSelected(tab)
        }

        override fun onTabUnselected(tab: TabLayout.Tab?) {}
        override fun onTabReselected(tab: TabLayout.Tab?) {}
    })
}