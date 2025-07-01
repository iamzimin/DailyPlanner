package com.evg.todo_list.presentation.mvi

import com.evg.todo_list.presentation.model.TaskGroup
import java.time.LocalDate

data class ToDoListState(
    val eventsInCurrentMonth: List<LocalDate> = emptyList(),
    val tasksInSelectedDate: List<TaskGroup> = emptyList(),
)