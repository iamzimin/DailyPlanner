package com.evg.todo_list.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
fun FullDayCard(
    task: TaskGroup.FullDay,
    onTaskDescriptionScreen: (Task) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onTaskDescriptionScreen(task.task) },
        colors = CardDefaults.cardColors().copy(
            containerColor = AppTheme.colors.tileBackground,
        )
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                text = task.task.name,
                color = AppTheme.colors.text,
                style = AppTheme.typography.body,
            )
        }
    }
}

@Preview
@Composable
private fun FullDayCardPreview() {
    DailyPlannerTheme {
        FullDayCard(
            task = TaskGroup.FullDay(
                task = Task(
                    id = 2,
                    dateStart = 1751446800000,
                    dateFinish = 1751461200000,
                    name = "Задача 2 июля 13:00‑17:00",
                    description = "Описание задачи на начало июля",
                )
            ),
            onTaskDescriptionScreen = {},
        )
    }
}