package com.evg.todo_list.presentation.model

import com.evg.todo_list.domain.model.Task

sealed class TaskGroup {
    data class FullDay(val task: Task) : TaskGroup()
    data class Hour(val hour: Int, val tasks: List<Task>) : TaskGroup()
}