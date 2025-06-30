package com.evg.database.data.repository

import com.evg.database.data.storage.TasksDatabase
import com.evg.database.domain.model.TaskDBO
import com.evg.database.domain.repository.DatabaseRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DatabaseRepositoryImpl(
    private val tasksDatabase: TasksDatabase,
) : DatabaseRepository {
    override suspend fun getAllTasks(): List<TaskDBO> {
        return tasksDatabase.tasksDao.getAllTasks()
    }

    override suspend fun insertTask(task: TaskDBO) {
        tasksDatabase.tasksDao.insertTask(task = task)
    }
}