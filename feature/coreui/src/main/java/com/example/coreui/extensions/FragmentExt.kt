package com.example.coreui.extensions

import androidx.fragment.app.Fragment
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

fun <T> Fragment.collectWhenStarted(flow: Flow<T>, block: suspend (value: T) -> Unit) =
    flow.flowWithLifecycle(lifecycle)
        .onEach(block)
        .launchIn(viewLifecycleOwner.lifecycleScope)