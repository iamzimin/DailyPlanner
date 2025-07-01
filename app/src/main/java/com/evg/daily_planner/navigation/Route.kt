package com.evg.daily_planner.navigation

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable data object ToDoList: Route
    @Serializable data class TaskDescription(val task: com.evg.task_description.presentation.model.TaskDescription): Route
    @Serializable data object TaskCreation: Route
}
