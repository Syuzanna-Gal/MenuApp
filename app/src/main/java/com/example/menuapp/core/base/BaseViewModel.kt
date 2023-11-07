package com.example.menuapp.core.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.menuapp.core.navigation.Command
import com.example.menuapp.util.event.InfoEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    private val _command by lazy { Channel<Command>(Channel.BUFFERED) }
    val command = _command.receiveAsFlow()

    private val _infoEvent = MutableSharedFlow<InfoEvent>()
    val infoEvent = _infoEvent.asSharedFlow()

    protected fun sendCommand(command: Command) {
        viewModelScope.launch { _command.send(command) }
    }

    fun emitInfoEvent(event: InfoEvent) {
        viewModelScope.launch { _infoEvent.emit(event) }
    }

}