package com.example.domain.delegate

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChangeTabDelegate @Inject constructor() {

    private val _tabRes = MutableSharedFlow<Int>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val tabRes = _tabRes.asSharedFlow()

    suspend fun setCurrentTabRes(tabRes: Int) {
        _tabRes.emit(tabRes)
    }
}