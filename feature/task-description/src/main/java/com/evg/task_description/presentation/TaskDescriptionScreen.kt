package com.evg.task_description.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.evg.resource.R
import com.evg.task_description.presentation.model.TaskDescription
import com.evg.ui.theme.AppTheme
import com.evg.ui.theme.BorderRadius
import com.evg.ui.theme.DailyPlannerTheme
import com.evg.ui.theme.HorizontalPadding
import com.evg.ui.theme.VerticalPadding

@Composable
fun TaskDescriptionScreen(
    modifier: Modifier,
    taskDescription: TaskDescription,
) {
    val titleSpace = 8.dp
    val fieldSpace = 18.dp

    Column(
        modifier = modifier
            .padding(
                horizontal = HorizontalPadding,
                vertical = VerticalPadding,
            )
            .verticalScroll(rememberScrollState()),
    ) {
        Text(
            text = stringResource(R.string.task_name),
            color = AppTheme.colors.text,
            style = AppTheme.typography.body,
        )
        Spacer(modifier = Modifier.height(titleSpace))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(BorderRadius))
                .background(AppTheme.colors.tileBackground)
                .padding(titleSpace),
            text = taskDescription.name,
            color = AppTheme.colors.text,
            style = AppTheme.typography.body,
        )
        Spacer(modifier = Modifier.height(fieldSpace))

        Text(
            text = stringResource(R.string.task_description),
            color = AppTheme.colors.text,
            style = AppTheme.typography.body,
        )
        Spacer(modifier = Modifier.height(titleSpace))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(BorderRadius))
                .background(AppTheme.colors.tileBackground)
                .padding(titleSpace),
            text = taskDescription.description,
            color = AppTheme.colors.text,
            style = AppTheme.typography.body,
        )
        Spacer(modifier = Modifier.height(fieldSpace))

        Text(
            text = stringResource(R.string.task_time),
            color = AppTheme.colors.text,
            style = AppTheme.typography.body,
        )
        Spacer(modifier = Modifier.height(titleSpace))

        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(BorderRadius))
                .background(AppTheme.colors.tileBackground)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            TimeDisplay(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = titleSpace),
                currentTimestamp = taskDescription.dateStart,
            )

            Icon(
                modifier = Modifier
                    .size(24.dp)
                    .graphicsLayer(scaleX = -1f),
                painter = painterResource(R.drawable.arrow_back),
                contentDescription = null,
                tint = AppTheme.colors.text,
            )

            TimeDisplay(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = titleSpace),
                currentTimestamp = taskDescription.dateFinish,
            )
        }
    }
}

@Preview
@Composable
private fun TaskDescriptionScreenPreview() {
    DailyPlannerTheme {
        TaskDescriptionScreen(
            modifier = Modifier,
            taskDescription = TaskDescription(
                id = 2,
                dateStart = 1751446800000,
                dateFinish = 1751461200000,
                name = "Задача 2 июля 13:00‑17:00",
                description = "Описание задачи на начало июля",
            ),
        )
    }
}