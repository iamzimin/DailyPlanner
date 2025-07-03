package com.evg.todo_list.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.evg.todo_list.domain.model.Task
import com.evg.todo_list.presentation.model.TaskGroup
import com.evg.ui.theme.AppTheme
import com.evg.ui.theme.DailyPlannerTheme

@Composable
fun HourCard(
    task: TaskGroup.Hour,
    onTaskDescriptionScreen: (Task) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            modifier = if (task.tasks.isEmpty()) {
                Modifier
            } else {
                Modifier.padding(top = 20.dp)
            },
            text = "%02d:00".format(
                task.hour,
                (task.hour + 1) % 24
            ),
            color = AppTheme.colors.text,
            style = AppTheme.typography.body,
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            if (task.tasks.isEmpty()) {
                HorizontalDivider(
                    modifier = Modifier.padding(top = 18.dp),
                    color = AppTheme.colors.text.copy(alpha = 0.7f),
                )
            } else {
                task.tasks.forEach { task ->
                    TaskCard(
                        task = task,
                        onTaskDescriptionScreen = onTaskDescriptionScreen
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun HourCardPreview() {
    DailyPlannerTheme(darkTheme = true) {
        val task = Task(
            id = 1,
            dateStart = 1748822400000,
            dateFinish = 1748836800000,
            name = "Задача 2 июля 04:00‑08:00",
            description = "Описание задачи на начало июля",
        )

        Surface(color = AppTheme.colors.background) {
            Column {
                HourCard(
                    task = TaskGroup.Hour(
                        hour = 0,
                        tasks = listOf(task, task)
                    ),
                    onTaskDescriptionScreen = {},
                )
                HourCard(
                    task = TaskGroup.Hour(
                        hour = 1,
                        tasks = listOf()
                    ),
                    onTaskDescriptionScreen = {},
                )
                HourCard(
                    task = TaskGroup.Hour(
                        hour = 3,
                        tasks = listOf()
                    ),
                    onTaskDescriptionScreen = {},
                )
                HourCard(
                    task = TaskGroup.Hour(
                        hour = 4,
                        tasks = listOf(task)
                    ),
                    onTaskDescriptionScreen = {},
                )
            }
        }
    }
}