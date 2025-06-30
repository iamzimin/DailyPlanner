package com.evg.todo_list.domain.mapper

import com.evg.database.domain.model.TaskDBO
import com.evg.todo_list.domain.model.Task

fun TaskDBO.toTask(): Task {
    return Task(
        id = this.id,
        dateStart = this.dateStart,
        dateFinish = this.dateFinish,
        name = this.name,
        description = this.description,
    )
}