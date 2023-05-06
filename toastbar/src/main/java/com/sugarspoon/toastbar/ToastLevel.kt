package com.sugarspoon.toastbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.ui.graphics.vector.ImageVector

enum class ToastLevel(val icon: ImageVector) {
    INFO(icon = Icons.Outlined.Info),
    WARNING(icon = Icons.Outlined.Warning),
    ERROR(icon = Icons.Outlined.Close),
    SUCCESS(icon = Icons.Outlined.Check)
}