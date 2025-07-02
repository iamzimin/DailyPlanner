package com.evg.task_creation.presentation.mvi

data class TaskCreationState(
    val paramOne: String = "default",
    val paramTwo: List<String> = emptyList(),
)