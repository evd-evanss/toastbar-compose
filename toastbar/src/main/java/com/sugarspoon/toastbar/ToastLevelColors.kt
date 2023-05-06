package com.sugarspoon.toastbar

import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import com.sugarspoon.toastbar.colors.ErrorDark
import com.sugarspoon.toastbar.colors.ErrorLight
import com.sugarspoon.toastbar.colors.InfoDark
import com.sugarspoon.toastbar.colors.InfoLight
import com.sugarspoon.toastbar.colors.SuccessDark
import com.sugarspoon.toastbar.colors.SuccessLight
import com.sugarspoon.toastbar.colors.WarningDark
import com.sugarspoon.toastbar.colors.WarningLight

@Stable
class ToastLevelColors(
    val error: Color,
    val success: Color,
    val info: Color ,
    val warning: Color
) {
    companion object {
        fun colors(
            isDarkTheme: Boolean = false,
            error: Color = if(isDarkTheme) ErrorDark else ErrorLight,
            success: Color = if(isDarkTheme) SuccessDark else SuccessLight,
            info: Color = if(isDarkTheme) InfoDark else InfoLight,
            warning: Color = if(isDarkTheme) WarningDark else WarningLight
        ) = ToastLevelColors(
            error = error,
            success = success,
            info = info,
            warning = warning
        )
    }
}