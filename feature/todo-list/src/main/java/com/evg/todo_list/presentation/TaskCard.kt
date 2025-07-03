package com.evg.todo_list.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.evg.todo_list.domain.model.Task
import com.evg.todo_list.presentation.mvi.ToDoListViewModel
import com.evg.ui.extensions.clickableRipple
import com.evg.ui.theme.AppTheme
import com.evg.ui.theme.BorderRadius
import com.evg.ui.theme.DailyPlannerTheme
import java.time.Instant
import java.time.format.DateTimeFormatter

@Composable
fun TaskCard(
    task: Task,
    onTaskDescriptionScreen: (Task) -> Unit,
) {
    val formatter = remember { DateTimeFormatter.ofPattern("dd.MM.yy HH:mm") }
    val start = Instant.ofEpochMilli(task.dateStart).atZone(ToDoListViewModel.ZONE).toLocalDateTime()
    val end = Instant.ofEpochMilli(task.dateFinish).atZone(ToDoListViewModel.ZONE).toLocalDateTime()

    Row(
        modifier = Modifier
            .height(60.dp)
    ) {
        Card(
            modifier = Modifier
                .clip(RoundedCornerShape(BorderRadius))
                .clickableRipple { onTaskDescriptionScreen(task) },
            colors = CardDefaults.cardColors().copy(
                containerColor = AppTheme.colors.tileBackground,
            )
        ) {
            Box {
                VerticalDivider(
                    color = AppTheme.colors.primary,
                    thickness = 4.dp,
                )
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            vertical = 8.dp,
                            horizontal = 12.dp,
                        ),
                    verticalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = task.name,
                        color = AppTheme.colors.text,
                        style = AppTheme.typography.body,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Text(
                        text = "${start.format(formatter)} - ${end.format(formatter)}",
                        color = AppTheme.colors.text,
                        style = AppTheme.typography.body,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun TaskCardPreview() {
    DailyPlannerTheme {
        TaskCard(
            task = Task(
                id = 2,
                dateStart = 1751446800000,
                dateFinish = 1751461200000,
                name = "Задача 2 июля 13:00‑17:00",
                description = "Описание задачи на начало июля",
            ),
            onTaskDescriptionScreen = {},
        )
    }
}