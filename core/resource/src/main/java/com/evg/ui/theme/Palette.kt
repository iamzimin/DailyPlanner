package com.evg.ui.theme

import androidx.compose.ui.graphics.Color

data class AppPalette(
    val primary: Color,
    val secondary: Color,
    val background: Color,

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
    primary = Color(0xFF7CAA68),
    secondary = Color(0xFF2D6330),
    background = Color(0xFF102418),

    text = Color(0xFFFFFFFF),

    // TextField
    textFieldPlaceholder = Color(0xFF4A7F52),
    textFieldTitle = Color(0xFFAAAAAA),
    tileBackground = Color(0xFF1B3D2F),
)

val greenLightPalette = AppPalette(
    primary = Color(0xFF9CDE7F),
    secondary = Color(0xFFBCE8AA),
    background = Color(0xFFFFFFFF),

    text = Color(0xFF000000),

    // TextField
    textFieldPlaceholder = Color(0xFF4A7F52),
    textFieldTitle = Color(0xFF666666),
    tileBackground = Color(0xFFE3F5E5),
)