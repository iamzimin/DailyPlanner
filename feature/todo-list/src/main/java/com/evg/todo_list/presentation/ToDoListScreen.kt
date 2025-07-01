package com.evg.todo_list.presentation

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.evg.todo_list.presentation.model.TaskGroup
import com.evg.todo_list.presentation.mvi.ToDoListAction
import com.evg.todo_list.presentation.mvi.ToDoListState
import com.evg.todo_list.presentation.mvi.ToDoListViewModel
import com.evg.ui.theme.AppTheme
import com.evg.ui.theme.DailyPlannerTheme
import com.evg.ui.theme.VerticalPadding
import dev.alejo.compose_calendar.SimpleComposeCalendar

@Composable
fun ToDoListScreen(
    state: ToDoListState,
    dispatch: (ToDoListAction) -> Unit,
    onTaskCreationScreen: () -> Unit,
    onTaskDescriptionScreen: () -> Unit,
) {
    var currentMonth by remember { mutableStateOf(ToDoListViewModel.CURRENT_DATE.withDayOfMonth(1)) }
    val tasksInSelectedDate = state.tasksInSelectedDate

    Column(
        modifier = Modifier
            .padding(vertical = VerticalPadding)
    ) {
        SimpleComposeCalendar(
            modifier = Modifier.fillMaxWidth(),
            initDate = currentMonth,
            events = state.eventsInCurrentMonth,
            onDayClick = { date, dayEvents ->
                Log.d("CalendarExample", "Выбрана дата: $date, событий: ${dayEvents.size}")
                dispatch(ToDoListAction.SelectDate(date = date))
            },
            eventIndicator = { event, position, count ->
                val eventColor = AppTheme.colors.primary
                Canvas(
                    modifier = Modifier
                        .padding(2.dp)
                        .size(5.dp)
                ) {
                    drawCircle(
                        color = eventColor,
                        radius = size.minDimension / 2,
                    )
                }
            },
            onPreviousMonthClick = {
                currentMonth = currentMonth.minusMonths(1)
                dispatch(ToDoListAction.ChangeMonth(newFirstOfMonth = currentMonth))
            },
            onNextMonthClick = {
                currentMonth = currentMonth.plusMonths(1)
                dispatch(ToDoListAction.ChangeMonth(newFirstOfMonth = currentMonth))
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(
                count = tasksInSelectedDate.size,
            ) { id ->
                val currentTask = tasksInSelectedDate[id]
                when (currentTask) {
                    is TaskGroup.FullDay -> {
                        FullDayCard(
                            task = currentTask,
                            onTaskDescriptionScreen = onTaskDescriptionScreen,
                        )
                    }

                    is TaskGroup.Hour -> {
                        HourCard(
                            task = currentTask,
                            onTaskDescriptionScreen = onTaskDescriptionScreen,
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun ToDoListScreenPreview() {
    DailyPlannerTheme {
        ToDoListScreen(
            state = ToDoListState(),
            dispatch = {},
            onTaskCreationScreen = {},
            onTaskDescriptionScreen = {},
        )
    }
}
