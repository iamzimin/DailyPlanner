package com.evg.daily_planner.navigation

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable data object ToDoList: Route
    @Serializable data object TaskDescription: Route
    @Serializable data object TaskCreation: Route
}
