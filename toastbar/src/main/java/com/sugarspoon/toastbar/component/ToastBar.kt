package com.sugarspoon.toastbar.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.semantics.LiveRegionMode
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.liveRegion
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.sugarspoon.toastbar.AnimateToast
import com.sugarspoon.toastbar.ToastAlignment
import com.sugarspoon.toastbar.ToastLevel
import com.sugarspoon.toastbar.ToastLevelColors
import com.sugarspoon.toastbar.ToastState
import com.sugarspoon.toastbar.ToastTimer

/**
 *
 * ToastBar
 *
 * @param modifier Modifications of padding and positioning.
 *
 * @param toastState Manipulates the visibility state of the toast.
 *
 * @param message Message to be displayed.
 *
 * @param textStyle Text style to be applied to the message.
 *
 * @param timerDuration Length of time the message will remain active.
 *
 * @param iconStatusIsVisible Show or hide the status icon defined in [toastLevel]
 *
 * @param toastLevelColors Set background color according to levels.
 *
 * @param toastLevel Toast message level, can be [INFO, WARNING, ERROR, SUCCESS].
 *
 * @param alignment Determines where the message will be aligned. [Top, Bottom]
 *
 * @param onDismiss Message closing listener.
 *
 */
@Composable
fun ToastBar(
    modifier: Modifier = Modifier,
    toastState: ToastState,
    message: String,
    textStyle: TextStyle = MaterialTheme.typography.bodySmall,
    timerDuration: Long = 2000,
    iconStatusIsVisible: Boolean = true,
    toastLevelColors: ToastLevelColors = ToastLevelColors.colors(),
    toastLevel: ToastLevel = ToastLevel.INFO,
    alignment: ToastAlignment = ToastAlignment.TOP,
    onDismiss: () -> Unit = {},
) {
    ToastBarImpl(
        modifier = modifier,
        message = message,
        iconStatusIsVisible = iconStatusIsVisible,
        toastLevel = toastLevel,
        textStyle = textStyle,
        timerDuration = timerDuration,
        toastLevelColors = toastLevelColors,
        toastState = toastState,
        toastAlignment = alignment,
        onDismiss = onDismiss,
    )
}

@Composable
private fun ToastBarImpl(
    modifier: Modifier,
    toastState: ToastState,
    message: String,
    textStyle: TextStyle,
    iconStatusIsVisible: Boolean = true,
    toastLevel: ToastLevel,
    toastLevelColors: ToastLevelColors,
    timerDuration: Long,
    toastAlignment: ToastAlignment,
    onDismiss: () -> Unit = {},
) {
    val showToastBar = remember { mutableStateOf(false) }
    val isClosed = remember {
        mutableStateOf(false)
    }

    ToastTimer(
        key = toastState.isOpen,
        timerDuration = timerDuration,
        onInit = {
            showToastBar.value = toastState.isOpen
        },
        isClosed = isClosed.value,
        onFinish = {
            showToastBar.value = false
            toastState.close()
            onDismiss()
        }
    )
    val toastColor = when (toastLevel) {
        ToastLevel.ERROR -> toastLevelColors.error
        ToastLevel.SUCCESS -> toastLevelColors.success
        ToastLevel.INFO -> toastLevelColors.info
        else -> toastLevelColors.warning
    }

    Box(
        contentAlignment = when (toastAlignment) {
            ToastAlignment.TOP -> Alignment.TopCenter
            ToastAlignment.BOTTOM -> Alignment.BottomEnd
        },
        modifier = modifier
            .fillMaxSize()
            .background(Color.Transparent)
            .clip(Shape)
    ) {
        AnimateToast(isDisplayed = showToastBar.value, toastAlignment = toastAlignment) {
            Row(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surface)
                    .background(toastColor)
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 72.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (iconStatusIsVisible) {
                    Icon(
                        imageVector = toastLevel.icon,
                        contentDescription = null,
                        modifier = Modifier.padding(start = DefaultPadding),
                    )
                }
                Text(
                    message,
                    style = textStyle,
                    modifier = Modifier
                        .padding(DefaultPadding)
                        .weight(1f)
                        .clearAndSetSemantics {
                            contentDescription = message
                            liveRegion = LiveRegionMode.Polite
                        }
                )
            }
        }
    }
}

@Composable
fun rememberToastState(): ToastState {
    return remember { ToastState() }
}

private val DefaultPadding = 16.dp

private val Shape = RectangleShape












