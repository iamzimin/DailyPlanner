package com.evg.task_creation.presentation

import android.icu.util.Calendar
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.evg.resource.R
import com.evg.ui.extensions.clickableRipple
import com.evg.ui.theme.AppTheme
import com.evg.ui.theme.BorderRadius
import com.evg.ui.theme.DailyPlannerTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDateTimeSelection(
    modifier: Modifier = Modifier,
    currentTimestamp: Long,
    onTimeSelected: (Long) -> Unit,
) {
    val dateFormatter = remember {
        SimpleDateFormat("EEEE, d MMMM yyyy", Locale.getDefault())
    }
    val timeFormatter = remember {
        SimpleDateFormat("HH:mm", Locale.getDefault())
    }

    var showDatePicker by remember { mutableStateOf(false) }
    var showTimePicker by remember { mutableStateOf(false) }

    val calendar = remember { Calendar.getInstance() }
    calendar.timeInMillis = currentTimestamp

    val textPadding = 8.dp

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            modifier = Modifier
                .clip(RoundedCornerShape(BorderRadius))
                .fillMaxWidth()
                .padding(vertical = textPadding)
                .clickableRipple { showDatePicker = true },
            text = dateFormatter.format(Date(currentTimestamp)).replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(Locale.getDefault())
                else it.toString()
            },
            textAlign = TextAlign.Center,
            style = AppTheme.typography.body,
            color = AppTheme.colors.text,
        )

        Text(
            modifier = Modifier
                .clip(RoundedCornerShape(BorderRadius))
                .fillMaxWidth()
                .padding(vertical = textPadding)
                .clickableRipple { showTimePicker = true },
            text = timeFormatter.format(Date(currentTimestamp)),
            textAlign = TextAlign.Center,
            style = AppTheme.typography.body,
            color = AppTheme.colors.text,
        )
    }

    if (showDatePicker) {
        val datePickerState = rememberDatePickerState(
            initialSelectedDateMillis = currentTimestamp,
        )
        val confirmEnabled by remember {
            derivedStateOf { datePickerState.selectedDateMillis != null }
        }
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            colors = DatePickerDefaults.colors().copy(
                containerColor = AppTheme.colors.background,
            ),
            confirmButton = {
                TextButton(
                    onClick = {
                        showDatePicker = false
                        datePickerState.selectedDateMillis?.let { selectedUtcMillis ->
                            val currentCal = Calendar.getInstance()
                            currentCal.timeInMillis = currentTimestamp

                            val selectedCal = Calendar.getInstance()
                            selectedCal.timeInMillis = selectedUtcMillis

                            currentCal.set(Calendar.YEAR, selectedCal.get(Calendar.YEAR))
                            currentCal.set(Calendar.MONTH, selectedCal.get(Calendar.MONTH))
                            currentCal.set(Calendar.DAY_OF_MONTH, selectedCal.get(Calendar.DAY_OF_MONTH))
                            onTimeSelected(currentCal.timeInMillis)
                        }
                    },
                    enabled = confirmEnabled,
                ) {
                    Text(
                        text = stringResource(R.string.save),
                        color = AppTheme.colors.text,
                        style = AppTheme.typography.body,
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { showDatePicker = false }
                ) {
                    Text(
                        text = stringResource(R.string.cancel),
                        color = AppTheme.colors.text,
                        style = AppTheme.typography.body,
                    )
                }
            }
        ) {
            DatePicker(
                state = datePickerState,
                showModeToggle = false,
                colors = DatePickerDefaults.colors().copy(
                    containerColor = AppTheme.colors.background,
                    titleContentColor = AppTheme.colors.text,
                    headlineContentColor = AppTheme.colors.text,
                    weekdayContentColor = AppTheme.colors.text,
                    subheadContentColor = AppTheme.colors.text,
                    navigationContentColor = AppTheme.colors.text,

                    yearContentColor = AppTheme.colors.text,
                    currentYearContentColor = AppTheme.colors.primary,
                    selectedYearContentColor = AppTheme.colors.tileBackground,
                    selectedYearContainerColor = AppTheme.colors.primary,

                    dayContentColor = AppTheme.colors.text,
                    selectedDayContentColor = AppTheme.colors.text,
                    selectedDayContainerColor = AppTheme.colors.secondary,

                    todayContentColor = AppTheme.colors.primary,
                    todayDateBorderColor = AppTheme.colors.primary,

                    dividerColor = AppTheme.colors.secondary,
                ),
            )
        }
    }

    if (showTimePicker) {
        val currentHour = calendar.get(Calendar.HOUR_OF_DAY)
        val currentMinute = calendar.get(Calendar.MINUTE)
        val timePickerState = rememberTimePickerState(
            initialHour = currentHour,
            initialMinute = currentMinute,
            is24Hour = true,
        )
        AlertDialog(
            onDismissRequest = { showTimePicker = false },
            containerColor = AppTheme.colors.background,
            iconContentColor = AppTheme.colors.primary,
            titleContentColor = AppTheme.colors.text,
            textContentColor = AppTheme.colors.text,
            title = {
                Text(
                    text = stringResource(R.string.choose_time),
                    color = AppTheme.colors.text,
                    style = AppTheme.typography.heading,
                )
            },
            text = {
                TimePicker(
                    colors = TimePickerDefaults.colors().copy(
                        clockDialColor = AppTheme.colors.tileBackground,
                        selectorColor = AppTheme.colors.primary,
                        periodSelectorBorderColor = AppTheme.colors.text,

                        clockDialSelectedContentColor = AppTheme.colors.text,
                        clockDialUnselectedContentColor = AppTheme.colors.text,

                        timeSelectorSelectedContainerColor = AppTheme.colors.secondary,
                        timeSelectorUnselectedContainerColor = AppTheme.colors.tileBackground,
                        timeSelectorSelectedContentColor = AppTheme.colors.text,
                        timeSelectorUnselectedContentColor = AppTheme.colors.text
                    ),
                    state = timePickerState,
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        val timeCal = Calendar.getInstance()
                        timeCal.timeInMillis = currentTimestamp
                        timeCal.set(Calendar.HOUR_OF_DAY, timePickerState.hour)
                        timeCal.set(Calendar.MINUTE, timePickerState.minute)
                        timeCal.set(Calendar.SECOND, 0)
                        timeCal.set(Calendar.MILLISECOND, 0)
                        onTimeSelected(timeCal.timeInMillis)
                        showTimePicker = false
                    }
                ) {
                    Text(
                        text = stringResource(R.string.save),
                        color = AppTheme.colors.text,
                        style = AppTheme.typography.body,
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { showTimePicker = false }
                ) {
                    Text(
                        text = stringResource(R.string.cancel),
                        color = AppTheme.colors.text,
                        style = AppTheme.typography.body,
                    )
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CustomDateTimeSelectionPreview() {
    DailyPlannerTheme(darkTheme = true) {
        Surface(color = AppTheme.colors.background) {
            CustomDateTimeSelection(
                currentTimestamp = System.currentTimeMillis(),
                onTimeSelected = {},
            )
        }
    }
}