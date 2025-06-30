package com.evg.todo_list.presentation.mvi

import com.evg.todo_list.domain.model.Task
import java.time.LocalDate

data class ToDoListState(
    val eventsInCurrentMonth: List<LocalDate> = emptyList(),
    val tasksInSelectedDate: List<Task> = emptyList(),
)