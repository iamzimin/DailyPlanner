package com.evg.daily_planner.mapper

import com.evg.task_description.presentation.model.TaskDescription
import com.evg.todo_list.domain.model.Task

fun Task.toDescriptionTask(): TaskDescription {
    return TaskDescription(
        id = this.id,
        dateStart = this.dateStart,
        dateFinish = this.dateFinish,
        name = this.name,
        description = this.description,
    )
}