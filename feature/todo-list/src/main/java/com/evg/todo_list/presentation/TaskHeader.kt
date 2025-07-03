package com.evg.todo_list.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.evg.ui.theme.AppTheme
import com.evg.ui.theme.DailyPlannerTheme

@Composable
fun TaskHeader(
    title: String,
) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .background(AppTheme.colors.background)
            .padding(top = 8.dp),
        text = title,
        style = AppTheme.typography.heading2.copy(
            fontWeight = FontWeight.Bold,
        ),
        color = AppTheme.colors.text,
    )
}

@Preview
@Composable
private fun FullDayHeaderPreview() {
    DailyPlannerTheme {
        TaskHeader(
            title = "Title"
        )
    }
}