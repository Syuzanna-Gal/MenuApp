package com.example.menuapp.util.event

import com.example.menuapp.R
import com.example.menuapp.util.TextSource

sealed interface InfoEvent {
    data class Info(
        val title: TextSource = TextSource.Resource(R.string.something_went_wrong),
        val message: TextSource = TextSource,
        val buttonText: TextSource = TextSource.Resource(R.string.ok)
    ) : InfoEvent
}