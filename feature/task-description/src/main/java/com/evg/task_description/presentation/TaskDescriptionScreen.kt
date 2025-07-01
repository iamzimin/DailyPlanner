package com.evg.task_description.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.text
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.evg.task_description.presentation.model.TaskDescription
import com.evg.ui.theme.AppTheme
import com.evg.ui.theme.DailyPlannerTheme
import com.evg.ui.theme.HorizontalPadding
import com.evg.ui.theme.VerticalPadding
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale
import kotlin.text.format

@Composable
fun TaskDescriptionScreen(
    modifier: Modifier,
    taskDescription: TaskDescription,
) {
    val formatter = DateTimeFormatter
        .ofPattern("dd MMM yyyy, HH:mm", Locale.getDefault())
        .withZone(ZoneId.systemDefault())

    Column(
        modifier = modifier
            .padding(
                horizontal = HorizontalPadding,
                vertical = VerticalPadding,
            )
    ) {
        Text(
            text = "Name: ${taskDescription.name}",
            color = AppTheme.colors.text,
            style = AppTheme.typography.body,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Description: ${taskDescription.description}",
            color = AppTheme.colors.text,
            style = AppTheme.typography.body,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Start: ${formatter.format(Instant.ofEpochMilli(taskDescription.dateStart))}",
            color = AppTheme.colors.text,
            style = AppTheme.typography.body,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "End: ${formatter.format(Instant.ofEpochMilli(taskDescription.dateFinish))}",
            color = AppTheme.colors.text,
            style = AppTheme.typography.body,
        )
    }
}

@Preview
@Composable
private fun TaskDescriptionScreenPreview() {
    DailyPlannerTheme {
        TaskDescriptionScreen(
            modifier = Modifier,
            taskDescription = TaskDescription(
                id = 2,
                dateStart = 1751446800000,
                dateFinish = 1751461200000,
                name = "Задача 2 июля 13:00‑17:00",
                description = "Описание задачи на начало июля",
            ),
        )
    }
}