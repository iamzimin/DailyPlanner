package com.evg.todo_list.domain.repository

import com.evg.todo_list.domain.model.Task

interface ToDoListRepository {
    suspend fun getAllTasks(): List<Task>
}