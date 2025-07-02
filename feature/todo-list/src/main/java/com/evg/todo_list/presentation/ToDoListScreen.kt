package com.evg.todo_list.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.evg.resource.R
import com.evg.todo_list.domain.model.Task
import com.evg.todo_list.presentation.model.TaskGroup
import com.evg.todo_list.presentation.mvi.ToDoListAction
import com.evg.todo_list.presentation.mvi.ToDoListState
import com.evg.todo_list.presentation.mvi.ToDoListViewModel
import com.evg.ui.custom.RoundedButton
import com.evg.ui.theme.AppTheme
import com.evg.ui.theme.ButtonPadding
import com.evg.ui.theme.DailyPlannerTheme
import com.evg.ui.theme.HorizontalPadding
import com.evg.ui.theme.VerticalPadding
import dev.alejo.compose_calendar.SimpleComposeCalendar

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.ToDoListScreen(
    modifier: Modifier,
    animatedVisibilityScope: AnimatedVisibilityScope,
    state: ToDoListState,
    dispatch: (ToDoListAction) -> Unit,
    onTaskCreationScreen: () -> Unit,
    onTaskDescriptionScreen: (Task) -> Unit,
) {
    var currentMonth by remember { mutableStateOf(ToDoListViewModel.CURRENT_DATE.withDayOfMonth(1)) }
    val tasksInSelectedDate = state.tasksInSelectedDate

    Column(
        modifier = modifier
            .padding(
                horizontal = HorizontalPadding,
                vertical = VerticalPadding,
            )
    ) {
        SimpleComposeCalendar(
            modifier = Modifier.fillMaxWidth(),
            initDate = currentMonth,
            events = state.eventsInCurrentMonth,
            onDayClick = { date, dayEvents ->
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

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        RoundedButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(ButtonPadding)
                .sharedBounds(
                    sharedContentState = rememberSharedContentState(
                        key = "FAB_EXPLODE_BOUNDS_KEY"
                    ),
                    animatedVisibilityScope = animatedVisibilityScope,
                ),
            backgroundColor = AppTheme.colors.secondary,
            icon = painterResource(R.drawable.plus),
            iconColor = AppTheme.colors.primary,
            onClick = { onTaskCreationScreen() },
        )
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Preview
@Composable
private fun ToDoListScreenPreview() {
    DailyPlannerTheme {
        Surface(color = AppTheme.colors.background) {
            SharedTransitionLayout {
                AnimatedVisibility(visibleState = MutableTransitionState(true)) {
                    ToDoListScreen(
                        modifier = Modifier,
                        state = ToDoListState(),
                        animatedVisibilityScope = this,
                        dispatch = {},
                        onTaskCreationScreen = {},
                        onTaskDescriptionScreen = {},
                    )
                }
            }
        }
    }
}
