package com.evg.database.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TaskDBO(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val dateStart: Long,
    val dateFinish: Long,
    val name: String,
    val description: String,
)