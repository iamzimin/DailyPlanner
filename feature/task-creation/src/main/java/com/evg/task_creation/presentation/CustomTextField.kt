package com.evg.task_creation.presentation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import com.evg.ui.theme.AppTheme
import com.evg.ui.theme.BorderRadius
import com.evg.ui.theme.DailyPlannerTheme

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    title: String,
    text: String,
    onTextChange: (String) -> Unit,
    maxLines: Int,
) {
    TextField(
        modifier = modifier
            .clip(RoundedCornerShape(BorderRadius))
            .fillMaxWidth(),
        value = text,
        onValueChange = { onTextChange(it) },
        shape = RoundedCornerShape(BorderRadius),
        placeholder = {
            Text(
                text = title,
                color = AppTheme.colors.textFieldPlaceholder,
                style = AppTheme.typography.body,
            )
        },
        singleLine = maxLines == 1,
        maxLines = maxLines,
        colors = TextFieldDefaults.colors().copy(
            cursorColor = AppTheme.colors.primary,
            focusedTextColor = AppTheme.colors.text,
            focusedContainerColor = AppTheme.colors.tileBackground,
            focusedIndicatorColor = AppTheme.colors.primary,

            unfocusedTextColor = AppTheme.colors.text,
            unfocusedContainerColor = AppTheme.colors.tileBackground,
            unfocusedIndicatorColor = AppTheme.colors.primary,
        ),
    )
}

@Preview
@Composable
private fun CustomTextFieldPreview() {
    DailyPlannerTheme(darkTheme = true) {
        Surface(color = AppTheme.colors.background) {
            CustomTextField(
                title = "Title",
                text = "",
                onTextChange = {},
                maxLines = 1,
            )
        }
    }
}