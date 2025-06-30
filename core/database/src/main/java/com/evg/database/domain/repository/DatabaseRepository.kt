package com.evg.database.domain.repository

import com.evg.database.domain.model.TaskDBO

interface DatabaseRepository {
    suspend fun getAllTasks(): List<TaskDBO>
    suspend fun insertTask(task: TaskDBO)
}