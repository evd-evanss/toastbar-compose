package com.sugarspoon.toastbar.ui.sample

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrightnessMedium
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import com.sugarspoon.toastbar.R
import com.sugarspoon.toastbar.ToastAlignment
import com.sugarspoon.toastbar.ToastLevel
import com.sugarspoon.toastbar.ToastLevelColors
import com.sugarspoon.toastbar.colors.ErrorDark
import com.sugarspoon.toastbar.colors.InfoDark
import com.sugarspoon.toastbar.colors.InfoLight
import com.sugarspoon.toastbar.colors.SuccessDark
import com.sugarspoon.toastbar.colors.SuccessLight
import com.sugarspoon.toastbar.colors.WarningDark
import com.sugarspoon.toastbar.colors.WarningLight
import com.sugarspoon.toastbar.component.ToastBar
import com.sugarspoon.toastbar.component.rememberToastState
import com.sugarspoon.toastbar.ui.components.RadioGroup
import com.sugarspoon.toastbar.ui.theme.BorderColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Sample(isDarkTheme: MutableState<Boolean>) {
    val toastState = rememberToastState()
    val status = remember { mutableStateOf(ToastLevel.INFO) }
    val message = remember { mutableStateOf(LoremIpsum(8).values.first()) }
    val showIcon = remember { mutableStateOf(false) }
    val items =
        listOf(stringResource(R.string.align_to_top), stringResource(R.string.align_to_bottom))
    val (selected, setSelected) = remember { mutableStateOf(items.first()) }
    val alignment = remember { mutableStateOf(ToastAlignment.TOP) }
    alignment.value = when (selected) {
        stringResource(id = R.string.align_to_top) -> ToastAlignment.TOP
        else -> ToastAlignment.BOTTOM
    }
    Column {
        TopAppBar(
            title = {
                Text(
                    text = stringResource(id = R.string.app_name),
                    style = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.onPrimary),
                    modifier = Modifier,
                )
            },
            actions = {
                IconButton(
                    onClick = {
                        isDarkTheme.value = !isDarkTheme.value
                    }
                ) {
                    Icon(
                        Icons.Filled.BrightnessMedium,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            },
            colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
        )
        Text(
            text = stringResource(id = R.string.icon_visibility),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(start = DefaultPadding, top = DefaultPadding)
        )
        Row(modifier = Modifier, verticalAlignment = Alignment.CenterVertically) {
            Switch(
                checked = showIcon.value,
                onCheckedChange = { showIcon.value = it },
                modifier = Modifier.padding(horizontal = DefaultPadding)
            )
            Text(
                text = stringResource(R.string.icon_visibility_display),
                style = MaterialTheme.typography.bodySmall
            )
        }
        Text(
            text = stringResource(id = R.string.toast_alignment),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(start = DefaultPadding, top = DefaultPadding)
        )
        RadioGroup(
            items = items,
            selected = selected,
            setSelected = setSelected
        )
        Text(
            text = stringResource(id = R.string.toast_levels),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.stackPadding()
        )
        androidx.compose.material3.ListItem(
            headlineText = {
                Text(
                    text = stringResource(id = R.string.info_level),
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            trailingContent = {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = null,
                    tint = if (isSystemInDarkTheme()) InfoDark.copy(alpha = 1f) else InfoLight.copy(
                        alpha = 1f
                    )
                )
            },
            modifier = Modifier
                .stackPadding()
                .border(
                    width = BorderWidth,
                    color = BorderColor,
                    shape = RoundedCornerShape(RoundedCorner)
                )
                .clip(RoundedCornerShape(RoundedCorner))
                .clickable {
                    status.value = ToastLevel.INFO
                    toastState.display()
                },
            shadowElevation = ElevationLevel,
        )
        androidx.compose.material3.ListItem(
            headlineText = {
                Text(
                    text = stringResource(id = R.string.warning_level),
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            trailingContent = {
                Icon(
                    imageVector = Icons.Outlined.Warning,
                    contentDescription = null,
                    tint = if (isSystemInDarkTheme()) WarningDark.copy(alpha = 1f) else WarningLight.copy(
                        alpha = 1f
                    )
                )
            },
            modifier = Modifier
                .stackPadding()
                .border(
                    width = BorderWidth,
                    color = BorderColor,
                    shape = RoundedCornerShape(RoundedCorner)
                )
                .clip(RoundedCornerShape(RoundedCorner))
                .clickable {
                    status.value = ToastLevel.WARNING
                    toastState.display()
                },
            shadowElevation = ElevationLevel
        )
        androidx.compose.material3.ListItem(
            headlineText = {
                Text(
                    text = stringResource(id = R.string.error_level),
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            trailingContent = {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = null,
                    tint = if (isSystemInDarkTheme()) ErrorDark.copy(alpha = 1f) else com.sugarspoon.toastbar.ui.theme.ErrorLight.copy(
                        alpha = 1f
                    )
                )
            },
            modifier = Modifier
                .stackPadding()
                .border(
                    width = BorderWidth,
                    color = BorderColor,
                    shape = RoundedCornerShape(RoundedCorner)
                )
                .clip(RoundedCornerShape(RoundedCorner))
                .clickable {
                    status.value = ToastLevel.ERROR
                    toastState.display()
                }
                .clip(RoundedCornerShape(RoundedCorner)),
            shadowElevation = ElevationLevel
        )
        androidx.compose.material3.ListItem(
            headlineText = {
                Text(
                    text = stringResource(id = R.string.success_level),
                    style = MaterialTheme.typography.bodyMedium
                )
            }, trailingContent = {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    tint = if (isSystemInDarkTheme()) SuccessDark.copy(alpha = 1f) else SuccessLight.copy(
                        alpha = 1f
                    )
                )
            },
            modifier = Modifier
                .stackPadding()
                .border(
                    width = BorderWidth,
                    color = BorderColor,
                    shape = RoundedCornerShape(RoundedCorner)
                )
                .clip(RoundedCornerShape(RoundedCorner))
                .clickable {
                    status.value = ToastLevel.SUCCESS
                    toastState.display()
                }
                .clip(RoundedCornerShape(RoundedCorner)),
            shadowElevation = ElevationLevel
        )
    }
    ToastBar(
        toastState = toastState,
        message = message.value,
        toastLevel = status.value,
        timerDuration = Duration,
        alignment = alignment.value,
        toastLevelColors = ToastLevelColors.colors(isDarkTheme = isSystemInDarkTheme()),
        iconStatusIsVisible = showIcon.value
    )
}

private val DefaultPadding = 16.dp
private val ElevationLevel = 4.dp
private val RoundedCorner = 8.dp
private val BorderWidth = 1.dp
private const val Duration = 2000L
private fun Modifier.stackPadding() = composed {
    padding(start = DefaultPadding, end = DefaultPadding, bottom = DefaultPadding)
}