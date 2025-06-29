package com.evg.todo_list.presentation.mvi

sealed class ToDoListAction {
    data class FirstClass(val paramOne: String) : ToDoListAction()
    data object SecondObject : ToDoListAction()
}