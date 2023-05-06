package com.sugarspoon.toastbar

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class ToastState {

    var isOpen by mutableStateOf(false)
        private set

    fun display() {
        isOpen = true
    }

    internal fun close() {
        isOpen = false
    }
}
