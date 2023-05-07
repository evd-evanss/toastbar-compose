package com.sugarspoon.toastbar.typography

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.toFontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.sugarspoon.toastbar.R

val ToastBarTypography = Typography(
    titleMedium = TextStyle(
        fontFamily = Font(R.font.questrial_regular).toFontFamily(),
        fontSize = 16.sp,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight(700)
    ),
    titleSmall = TextStyle(
        fontFamily = Font(R.font.questrial_regular).toFontFamily(),
        fontSize = 14.sp,
        textAlign = TextAlign.Center
    ),
    bodyMedium = TextStyle(
        fontFamily = Font(R.font.questrial_regular).toFontFamily(),
        fontSize = 16.sp,
        textAlign = TextAlign.Left,
        fontWeight = FontWeight(600)
    ),
    bodySmall = TextStyle(
        fontFamily = Font(R.font.questrial_regular).toFontFamily(),
        fontSize = 16.sp,
        textAlign = TextAlign.Left,
        fontWeight = FontWeight(400)
    )
)