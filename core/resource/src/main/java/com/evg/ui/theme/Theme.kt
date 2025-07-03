package com.evg.ui.theme

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect

@Composable
fun DailyPlannerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    LaunchedEffect(AppTheme.nightMode) {
        AppCompatDelegate.setDefaultNightMode(AppTheme.nightMode)
    }

    val colors = when (darkTheme) {
        true -> {
            when (AppTheme.style) {
                AppStyle.BLUE -> blueDarkPalette
            }
        }
        false -> {
            when (AppTheme.style) {
                AppStyle.BLUE -> blueLightPalette
            }
        }
    }

    val typography = when(AppTheme.textSize) {
        AppSize.Medium -> mediumTextSize
    }

    CompositionLocalProvider(
        LocalAppColors provides colors,
        LocalAppTypography provides typography,
        content = content,
    )
}