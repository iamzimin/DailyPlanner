package com.evg.todo_list.data.repository

import com.evg.database.domain.model.TaskDBO
import com.evg.database.domain.repository.DatabaseRepository
import com.evg.todo_list.domain.model.Task
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class FakeDatabaseRepository : DatabaseRepository {
    var tasks: List<TaskDBO> = emptyList()

    override suspend fun getAllTasks(): List<TaskDBO> {
        return tasks
    }
    override suspend fun insertTask(task: TaskDBO) {
        TODO("Not yet implemented")
    }
}

class ToDoListRepositoryImplTest {

    private lateinit var fakeDatabaseRepository: FakeDatabaseRepository
    private lateinit var toDoListRepository: ToDoListRepositoryImpl

    @BeforeEach
    fun setup() {
        fakeDatabaseRepository = FakeDatabaseRepository()
        toDoListRepository = ToDoListRepositoryImpl(fakeDatabaseRepository)
    }

    @Test
    fun `getAllTasks should return correctly task list from database`() = runBlocking {
        // Given
        val taskDBO = TaskDBO(
            id = 5L,
            dateStart = 1_751_558_400L,
            dateFinish = 1_751_628_600L,
            name = "Test Task",
            description = "Test description example"
        )
        fakeDatabaseRepository.tasks = listOf(taskDBO)

        val expected = listOf(
            Task(
                id = 5L,
                dateStart = 1_751_558_400L,
                dateFinish = 1_751_628_600L,
                name = "Test Task",
                description = "Test description example"
            )
        )

        // When
        val actual = toDoListRepository.getAllTasks()

        // Then
        Assertions.assertEquals(expected, actual)
    }
}

