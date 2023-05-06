package com.sugarspoon.toastbar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable

@Composable
internal fun getEnterAnimation(toastAlignment: ToastAlignment): EnterTransition {
    return when (toastAlignment) {
        ToastAlignment.TOP -> slideInVertically(
            animationSpec = tween(durationMillis = 200)
        ) { fullHeight ->
            -fullHeight / 3
        } + fadeIn(animationSpec = tween(durationMillis = 200))

        ToastAlignment.BOTTOM -> slideInVertically(
            animationSpec = tween(durationMillis = 200)
        ) { fullHeight ->
            fullHeight / 3
        } + fadeIn(animationSpec = tween(durationMillis = 200))
    }
}

@Composable
internal fun getOutAnimation(toastAlignment: ToastAlignment): ExitTransition {
    return when(toastAlignment) {
        ToastAlignment.TOP -> slideOutVertically(
            targetOffsetY = { fullHeight ->
                - fullHeight / 3
            },
            animationSpec = tween(
                durationMillis = 100
            )
        )
        else -> slideOutVertically(
            targetOffsetY = { fullHeight ->
                fullHeight / 3
            },
            animationSpec = tween(
                durationMillis = 100
            )
        )
    }
}

@Composable
internal fun AnimateToast(
    isDisplayed: Boolean,
    toastAlignment: ToastAlignment,
    content: @Composable () -> Unit
) {
    AnimatedVisibility(
        visible = isDisplayed,
        enter = getEnterAnimation(toastAlignment = toastAlignment),
        exit = getOutAnimation(toastAlignment = toastAlignment),
    ) {
        content()
    }
}