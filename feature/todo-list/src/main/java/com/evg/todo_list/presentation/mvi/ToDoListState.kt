package com.evg.todo_list.presentation.mvi

data class ToDoListState(
    val paramOne: String = "default",
    val paramTwo: List<String> = emptyList(),
)