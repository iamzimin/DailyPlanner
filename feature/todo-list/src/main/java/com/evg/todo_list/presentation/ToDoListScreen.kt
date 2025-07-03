package com.evg.todo_list.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
import dev.alejo.compose_calendar.util.CalendarDefaults
import java.time.LocalDate

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
            calendarColors = CalendarDefaults.calendarColors().copy(
                backgroundColor = AppTheme.colors.tileBackground,
                contentColor = AppTheme.colors.text,
                headerBackgroundColor = AppTheme.colors.tileBackground,
                headerContentColor = AppTheme.colors.text,
                navigationContainerColor = AppTheme.colors.primary,
                navigationContentColor = AppTheme.colors.text,
                eventBackgroundColor = AppTheme.colors.secondary,
                eventContentColor = AppTheme.colors.text,
            ),
            onDayClick = { date, dayEvents ->
                dispatch(ToDoListAction.SelectDate(date = date))
            },
            eventIndicator = { event, position, count ->
                val maxVisibleDots = 2
                val dotSize = 5.dp
                val dotPadding = 1.dp
                val eventColor = AppTheme.colors.primary
                val textColor = AppTheme.colors.text

                if (position < maxVisibleDots) {
                    Canvas(
                        modifier = Modifier
                            .padding(dotPadding)
                            .size(dotSize)
                    ) {
                        drawCircle(
                            color = eventColor,
                            radius = size.minDimension / 2,
                        )
                    }
                } else if (position == maxVisibleDots && count > maxVisibleDots) {
                    Box(
                        modifier = Modifier
                            .padding(dotPadding)
                            .size(dotSize * 2),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "+${count - maxVisibleDots}",
                            color = textColor,
                            fontSize = 8.sp,
                        )
                    }
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

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            var fullDayHeaderShown = false
            var hourTasksHeaderShown = false

            tasksInSelectedDate.forEachIndexed { index, currentTask ->
                when (currentTask) {
                    is TaskGroup.FullDay -> {
                        if (!fullDayHeaderShown) {
                            stickyHeader {
                                TaskHeader(
                                    title = stringResource(R.string.full_day)
                                )
                            }
                            fullDayHeaderShown = true
                        }
                        item {
                            TaskCard(
                                task = currentTask.task,
                                onTaskDescriptionScreen = onTaskDescriptionScreen,
                            )
                        }
                    }

                    is TaskGroup.Hour -> {
                        if (!hourTasksHeaderShown) {
                            stickyHeader {
                                TaskHeader(
                                    title = stringResource(R.string.hourly_tasks)
                                )
                            }
                            hourTasksHeaderShown = true
                        }
                        item {
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
            iconColor = AppTheme.colors.text,
            onClick = { onTaskCreationScreen() },
        )
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Preview
@Composable
private fun ToDoListScreenPreview() {
    DailyPlannerTheme(darkTheme = true) {
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
