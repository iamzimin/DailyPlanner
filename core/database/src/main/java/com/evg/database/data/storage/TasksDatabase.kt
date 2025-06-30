package com.evg.database.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.evg.database.domain.model.TaskDBO

@Database(
    entities = [TaskDBO::class],
    version = 1,
    exportSchema = false,
)
abstract class TasksDatabase: RoomDatabase() {
    abstract val tasksDao: TasksDao

    companion object {
        const val DATABASE_NAME = "tasks"
    }
}