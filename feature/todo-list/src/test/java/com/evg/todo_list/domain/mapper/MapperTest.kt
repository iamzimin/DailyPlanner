package com.evg.todo_list.domain.mapper

import com.evg.database.domain.model.TaskDBO
import com.evg.todo_list.domain.model.Task
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TaskDBOToTaskTest {

    @Test
    fun `the test should return a correctly converted TaskDBO to Task`() {
        // Given
        val taskDBO = TaskDBO(
            id = 11L,
            dateStart = 1_751_558_400L,
            dateFinish = 1_751_628_600L,
            name = "Test Task",
            description = "Test description example"
        )

        val expected = Task(
            id = 11L,
            dateStart = 1_751_558_400L,
            dateFinish = 1_751_628_600L,
            name = "Test Task",
            description = "Test description example"
        )

        // When
        val actual = taskDBO.toTask()

        // Then
        Assertions.assertEquals(expected, actual)
    }
}