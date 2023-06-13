package com.example.domain.delegate

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class CurrentCityDelegate @Inject constructor() {

    private val _currentCity = MutableSharedFlow<String>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val currentCity = _currentCity.asSharedFlow()

    suspend fun setCurrentCity(city: String) {
        _currentCity.emit(city)
    }
}