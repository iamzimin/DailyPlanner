package com.evg.task_creation.domain.repository

import com.evg.task_creation.domain.model.Task

interface TaskCreationRepository {
    suspend fun insertTask(task: Task)
}