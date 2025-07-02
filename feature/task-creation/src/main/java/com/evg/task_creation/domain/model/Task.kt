package com.evg.task_creation.domain.model

data class Task(
    val name: String,
    val description: String,
    val dateStart: Long,
    val dateFinish: Long,
)