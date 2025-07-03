package com.evg.task_description.presentation.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.evg.task_description.presentation.model.TaskDescription

@Composable
fun TaskDescriptionRoot(
    modifier: Modifier,
    taskDescription: TaskDescription,
) {
    TaskDescriptionScreen(
        modifier = modifier,
        taskDescription = taskDescription,
    )
}