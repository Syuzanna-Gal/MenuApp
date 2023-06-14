package com.example.foodorderapp.util.event

import com.example.foodorderapp.R
import com.example.foodorderapp.util.TextSource

sealed interface InfoEvent {
    data class Info(
        val title: TextSource = TextSource.Resource(R.string.something_went_wrong),
        val message: TextSource = TextSource,
        val buttonText: TextSource = TextSource.Resource(R.string.ok)
    ) : InfoEvent
}