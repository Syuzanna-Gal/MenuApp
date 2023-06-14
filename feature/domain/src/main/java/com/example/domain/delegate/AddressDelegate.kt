package com.example.domain.delegate

import android.location.Address
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class CurrentAddressDelegate @Inject constructor() {

    private val _currentAddress = MutableSharedFlow<Address>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val currentAddress = _currentAddress.asSharedFlow()

    suspend fun setCurrentAddress(address: Address) {
        _currentAddress.emit(address)
    }
}