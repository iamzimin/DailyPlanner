package com.evg.task_creation.presentation.mvi

import com.evg.task_creation.domain.model.Task

sealed class TaskCreationAction {
    data class AddTask(val task: Task) : TaskCreationAction()
}