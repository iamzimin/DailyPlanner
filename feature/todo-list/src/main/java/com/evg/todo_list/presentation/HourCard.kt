package com.evg.todo_list.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.evg.todo_list.domain.model.Task
import com.evg.todo_list.presentation.model.TaskGroup
import com.evg.todo_list.presentation.mvi.ToDoListViewModel
import com.evg.ui.theme.AppTheme
import com.evg.ui.theme.DailyPlannerTheme
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Composable
fun HourCard(
    task: TaskGroup.Hour,
    onTaskDescriptionScreen: (Task) -> Unit,
) {
    val formatter = remember { DateTimeFormatter.ofPattern("HH:mm") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            "%02d:00 - %02d:00".format(
                task.hour,
                (task.hour + 1) % 24
            ),
            modifier = Modifier.width(100.dp),
            color = AppTheme.colors.text,
            style = AppTheme.typography.body,
        )
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            if (task.tasks.isEmpty()) {
                HorizontalDivider(color = Color.LightGray)
            } else {
                task.tasks.forEach { task ->
                    val start = Instant.ofEpochMilli(task.dateStart).atZone(ToDoListViewModel.ZONE).toLocalTime()
                    val end = Instant.ofEpochMilli(task.dateFinish).atZone(ToDoListViewModel.ZONE).toLocalTime()

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 2.dp)
                            .clickable { onTaskDescriptionScreen(task) },
                        colors = CardDefaults.cardColors().copy(
                            containerColor = AppTheme.colors.tileBackground,
                        )
                    ) {
                        Column(
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Text(
                                text = task.name,
                                color = AppTheme.colors.text,
                                style = AppTheme.typography.body,
                            )
                            Text(
                                text = "${start.format(formatter)} - ${end.format(formatter)}",
                                color = AppTheme.colors.text,
                                style = AppTheme.typography.body,
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun HourCardPreview() {
    DailyPlannerTheme {
        HourCard(
            task = TaskGroup.Hour(
                hour = 0,
                tasks = listOf(
                    Task(
                        id = 1,
                        dateStart = 1748822400000,
                        dateFinish = 1748836800000,
                        name = "Задача 2 июля 04:00‑08:00",
                        description = "Описание задачи на начало июля",
                    ),
                    Task(
                        id = 2,
                        dateStart = 1751446800000,
                        dateFinish = 1751461200000,
                        name = "Задача 2 июля 13:00‑17:00",
                        description = "Описание задачи на начало июля",
                    ),
                )
            ),
            onTaskDescriptionScreen = {},
        )
    }
}