package com.evg.task_creation.presentation.mvi

sealed class TaskCreationSideEffect {
    data class FirstClass(val paramOne: String) : TaskCreationSideEffect()
    data object FirstObject : TaskCreationSideEffect()
}