package com.evg.ui.theme

import androidx.compose.ui.graphics.Color

data class AppPalette(
    val primary: Color,
    val secondary: Color,
    val background: Color,
    val shimmer: Color,

    val text: Color,

    // TextField
    val textFieldPlaceholder: Color,
    val textFieldTitle: Color,

    val tileBackground: Color,
)

enum class AppStyle {
    GREEN,
}

val greenDarkPalette = AppPalette(
    primary = Color(0xFF92C47E),
    secondary = Color(0xFF2E7D32),
    background = Color(0xFF102418),
    shimmer = Color.LightGray,

    text = Color(0xFFFFFFFF),

    // TextField
    textFieldPlaceholder = Color(0xFF4A7F52),
    textFieldTitle = Color(0xFFAAAAAA),
    tileBackground = Color(0xFF1B3D2F),
)

val greenLightPalette = AppPalette(
    primary = Color(0xFF92C47E),
    secondary = Color(0xFF2E7D32),
    background = Color(0xFFFFFFFF),
    shimmer = Color.Gray,

    text = Color(0xFF000000),

    // TextField
    textFieldPlaceholder = Color(0xFF4A7F52),
    textFieldTitle = Color(0xFF666666),
    tileBackground = Color(0xFFE3F5E5),
)