package com.evg.ui.theme

import androidx.compose.ui.graphics.Color

data class AppPalette(
    val primary: Color,
    val secondary: Color,
    val background: Color,

    val text: Color,
    val textFieldPlaceholder: Color,

    val tileBackground: Color,
)

enum class AppStyle {
    BLUE,
}

val blueDarkPalette = AppPalette(
    primary = Color(0xFF6A7B8C),
    secondary = Color(0xFF3E4E5E),
    background = Color(0xFF1A222C),

    text = Color(0xFFE0E0E0),
    textFieldPlaceholder = Color(0xFF5A6A7A),

    tileBackground = Color(0xFF2C3A48),
)

val blueLightPalette = AppPalette(
    primary = Color(0xFF8FA3B8),
    secondary = Color(0xFFB0C0D0),
    background = Color(0xFFF0F4F8),

    text = Color(0xFF212121),
    textFieldPlaceholder = Color(0xFF788A9C),

    tileBackground = Color(0xFFD8E0E8),
)