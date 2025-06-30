package com.evg.database.data.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.evg.database.domain.model.TaskDBO

@Dao
interface TasksDao {
    @Query("SELECT * FROM TaskDBO")
    suspend fun getAllTasks(): List<TaskDBO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: TaskDBO)
}