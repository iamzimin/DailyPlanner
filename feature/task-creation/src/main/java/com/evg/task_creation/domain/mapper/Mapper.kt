package com.evg.task_creation.domain.mapper

import com.evg.database.domain.model.TaskDBO
import com.evg.task_creation.domain.model.Task

fun Task.toTaskDBO(): TaskDBO {
    return TaskDBO(
        dateStart = this.dateStart,
        dateFinish = this.dateFinish,
        name = this.name,
        description = this.description,
    )
}