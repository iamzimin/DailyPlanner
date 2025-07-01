package com.evg.todo_list.presentation.mvi

import androidx.lifecycle.ViewModel
import com.evg.todo_list.domain.model.Task
import com.evg.todo_list.domain.repository.ToDoListRepository
import com.evg.todo_list.presentation.model.TaskGroup
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import javax.inject.Inject

@HiltViewModel
class ToDoListViewModel @Inject constructor(
    private val toDoListRepository: ToDoListRepository,
) : ContainerHost<ToDoListState, ToDoListSideEffect>, ViewModel() {
    override val container = container<ToDoListState, ToDoListSideEffect>(ToDoListState())

    companion object {
        val CURRENT_DATE: LocalDate = LocalDate.now()
        val ZONE: ZoneId = ZoneId.systemDefault()
    }

    private var allTasks: List<Task> = emptyList()
    private var tasksInCurrentMonth: List<Task> = emptyList()

    init {
        intent {
            allTasks = toDoListRepository.getAllTasks()

            val firstOfMonth = CURRENT_DATE.withDayOfMonth(1)
            dispatch(ToDoListAction.ChangeMonth(firstOfMonth))
        }
    }

    fun dispatch(action: ToDoListAction) {
        when (action) {
            is ToDoListAction.ChangeMonth -> loadMonth(action.newFirstOfMonth)
            is ToDoListAction.SelectDate -> loadTasksForDate(action.date)
        }
    }

    private fun loadMonth(firstOfMonth: LocalDate) = intent {
        val startOfDay = firstOfMonth.atStartOfDay(ZONE).toInstant().toEpochMilli()
        val endOfDay = firstOfMonth.plusMonths(1).atStartOfDay(ZONE).toInstant().toEpochMilli()

        tasksInCurrentMonth = allTasks.filter { task ->
            task.dateStart < endOfDay && task.dateFinish >= startOfDay
        }

        val events = tasksInCurrentMonth
            .flatMap { task ->
                val taskStart = Instant.ofEpochMilli(task.dateStart).atZone(ZONE).toLocalDate()
                val taskEnd = Instant.ofEpochMilli(task.dateFinish).atZone(ZONE).toLocalDate()
                val from = maxOf(taskStart, firstOfMonth)
                val to = minOf(taskEnd, firstOfMonth.plusMonths(1).minusDays(1))
                generateSequence(from) { previous ->
                    if (previous.isBefore(to)) previous.plusDays(1) else null
                }.toList()
            }

        reduce {
            state.copy(eventsInCurrentMonth = events)
        }
    }

    private fun loadTasksForDate(date: LocalDate) = intent {
        val startOfDay = date.atStartOfDay(ZONE).toInstant().toEpochMilli()
        val endOfDay = date.plusDays(1).atStartOfDay(ZONE).toInstant().toEpochMilli()

        val tasksForDay = tasksInCurrentMonth.filter { task ->
            task.dateStart < endOfDay && task.dateFinish > startOfDay
        }
        val (fullDayTasks, partial) = tasksForDay.partition { task ->
            task.dateStart <= startOfDay && task.dateFinish >= endOfDay
        }

        val dayStartMillis = date.atStartOfDay(ZONE).toInstant().toEpochMilli()
        val oneHourInMillis = 60 * 60 * 1000L

        val hours = (0 until 24).map { hour ->
            val hourStart = dayStartMillis + hour * 60 * 60_000L
            val hourEnd = hourStart + oneHourInMillis

            val tasks = partial.filter { task ->
                task.dateStart < hourEnd && task.dateFinish > hourStart
            }
            TaskGroup.Hour(hour, tasks)
        }

        val result = buildList {
            addAll(fullDayTasks.map { TaskGroup.FullDay(it) })
            addAll(hours)
        }

        reduce {
            state.copy(tasksInSelectedDate = result)
        }
    }
}