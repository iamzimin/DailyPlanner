package com.evg.todo_list.presentation.mvi

import java.time.LocalDate

sealed class ToDoListAction {
    data class ChangeMonth(val newFirstOfMonth: LocalDate) : ToDoListAction()
    data class SelectDate(val date: LocalDate) : ToDoListAction()
}