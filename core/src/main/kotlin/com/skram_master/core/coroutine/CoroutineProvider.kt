package com.skram_master.core.coroutine

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.jetbrains.annotations.VisibleForTesting
import kotlin.coroutines.CoroutineContext

sealed class CoroutineProvider(
    defaultDispatcher: CoroutineDispatcher,
) {
    @set:VisibleForTesting
    var dispatcherHandler: ((CoroutineContext) -> CoroutineContext)? = null
    val dispatcher = dispatcherHandler?.invoke(defaultDispatcher) ?: defaultDispatcher

    @VisibleForTesting
    fun reset() {
        dispatcherHandler = null
    }

    object Default : CoroutineProvider(Dispatchers.Default)
    object IO : CoroutineProvider(Dispatchers.IO)
    object Main : CoroutineProvider(Dispatchers.Main)
}
