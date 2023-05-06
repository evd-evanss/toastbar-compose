package com.sugarspoon.toastbar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import java.util.Timer
import kotlin.concurrent.schedule

@Composable
internal fun ToastTimer(
    key: Any,
    timerDuration: Long,
    isClosed: Boolean = false,
    onInit: () -> Unit,
    onFinish: () -> Unit
) {
    val timer = Timer("Toast Animation Timer", true)
    if(isClosed) timer.cancel()
    DisposableEffect(key1 = key) {
        onInit()
        timer.schedule(timerDuration) {
            onFinish()
        }
        onDispose {
            timer.cancel()
            timer.purge()
        }
    }
}