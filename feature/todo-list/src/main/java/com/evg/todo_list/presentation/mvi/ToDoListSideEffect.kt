package com.evg.todo_list.presentation.mvi

sealed class ToDoListSideEffect {
    data class FirstClass(val paramOne: String) : ToDoListSideEffect()
    data object FirstObject : ToDoListSideEffect()
}