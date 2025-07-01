package com.evg.daily_planner

import androidx.annotation.StringRes
import com.evg.daily_planner.navigation.Route
import com.evg.resource.R

sealed class TopBarTitle(
    val route: Route,
    @param:StringRes val title: Int,
) {
    companion object {
        val allTitles = listOf(TaskDescription, TaskCreation)
    }

    data object TaskDescription : TopBarTitle(
        route = Route.TaskDescription(
            task = com.evg.task_description.presentation.model.TaskDescription(
                id = 0,
                name = "",
                description = "",
                dateStart = 0,
                dateFinish = 0,
        )),
        title = R.string.task_description,
    )

    data object TaskCreation : TopBarTitle(
        route = Route.TaskCreation,
        title = R.string.task_creation,
    )
}
