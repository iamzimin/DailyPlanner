package com.evg.task_creation.data.repository

import com.evg.database.domain.repository.DatabaseRepository
import com.evg.task_creation.domain.mapper.toTaskDBO
import com.evg.task_creation.domain.model.Task
import com.evg.task_creation.domain.repository.TaskCreationRepository

class TaskCreationRepositoryImpl(
    private val databaseRepository: DatabaseRepository,
) : TaskCreationRepository {
    override suspend fun insertTask(task: Task) {
        return databaseRepository.insertTask(task = task.toTaskDBO())
    }
}