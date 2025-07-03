package com.evg.task_description.presentation.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.evg.ui.theme.AppTheme
import com.evg.ui.theme.BorderRadius
import com.evg.ui.theme.DailyPlannerTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun TimeDisplay(
    modifier: Modifier = Modifier,
    currentTimestamp: Long,
) {
    val dateFormatter = remember { SimpleDateFormat("E, d MMMM yyyy", Locale.getDefault()) }
    val timeFormatter = remember { SimpleDateFormat("HH:mm", Locale.getDefault()) }

    val textPadding = 8.dp

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            modifier = Modifier
                .clip(RoundedCornerShape(BorderRadius))
                .fillMaxWidth()
                .padding(vertical = textPadding),
            text = dateFormatter.format(Date(currentTimestamp)).replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(Locale.getDefault())
                else it.toString()
            },
            textAlign = TextAlign.Center,
            style = AppTheme.typography.body,
            color = AppTheme.colors.text,
        )

        Text(
            modifier = Modifier
                .clip(RoundedCornerShape(BorderRadius))
                .fillMaxWidth()
                .padding(vertical = textPadding),
            text = timeFormatter.format(Date(currentTimestamp)),
            textAlign = TextAlign.Center,
            style = AppTheme.typography.body,
            color = AppTheme.colors.text,
        )
    }
}

@Preview
@Composable
private fun TimeDisplayPreview() {
    DailyPlannerTheme {
        TimeDisplay(
            currentTimestamp = System.currentTimeMillis(),
        )
    }
}