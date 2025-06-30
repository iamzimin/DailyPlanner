package com.evg.todo_list.presentation.mvi

import androidx.lifecycle.ViewModel
import com.evg.todo_list.domain.model.Task
import com.evg.todo_list.domain.repository.ToDoListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import java.time.LocalDate
import java.time.Instant
import java.time.ZoneId
import javax.inject.Inject

@HiltViewModel
class ToDoListViewModel @Inject constructor(
    private val toDoListRepository: ToDoListRepository,
) : ContainerHost<ToDoListState, ToDoListSideEffect>, ViewModel() {
    override val container = container<ToDoListState, ToDoListSideEffect>(ToDoListState())

    companion object {
        val CURRENT_DATE: LocalDate = LocalDate.now()
    }

    private var allTasks: List<Task> = emptyList()
    private var tasksInCurrentMonth: List<Task> = emptyList()
    private val zone = ZoneId.systemDefault()

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
        val start = firstOfMonth.atStartOfDay(zone).toInstant().toEpochMilli()
        val end = firstOfMonth.plusMonths(1).atStartOfDay(zone).toInstant().toEpochMilli()

        tasksInCurrentMonth = allTasks.filter { task ->
            task.dateStart < end && task.dateFinish >= start
        }

        val events = tasksInCurrentMonth
            .flatMap { task ->
                val taskStart = Instant.ofEpochMilli(task.dateStart).atZone(zone).toLocalDate()
                val taskEnd = Instant.ofEpochMilli(task.dateFinish).atZone(zone).toLocalDate()
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
        val startOfDay = date.atStartOfDay(zone).toInstant().toEpochMilli()
        val endOfDay = date.plusDays(1).atStartOfDay(zone).toInstant().toEpochMilli()

        val tasks = tasksInCurrentMonth.filter { task ->
            task.dateStart < endOfDay && task.dateFinish >= startOfDay
        }

        reduce {
            state.copy(tasksInSelectedDate = tasks)
        }
    }
}