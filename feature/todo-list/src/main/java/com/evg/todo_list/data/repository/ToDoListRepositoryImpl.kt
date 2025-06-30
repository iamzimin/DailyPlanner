package com.evg.todo_list.data.repository

import com.evg.database.domain.repository.DatabaseRepository
import com.evg.todo_list.domain.mapper.toTask
import com.evg.todo_list.domain.model.Task
import com.evg.todo_list.domain.repository.ToDoListRepository

class ToDoListRepositoryImpl(
    private val databaseRepository: DatabaseRepository,
) : ToDoListRepository {
    override suspend fun getAllTasks(): List<Task> {
        return databaseRepository.getAllTasks().map { it.toTask() }
    }

}