package com.evg.task_creation.presentation

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.evg.resource.R
import com.evg.task_creation.domain.model.Task
import com.evg.task_creation.presentation.mvi.TaskCreationAction
import com.evg.ui.custom.RoundedButton
import com.evg.ui.theme.AppTheme
import com.evg.ui.theme.BorderRadius
import com.evg.ui.theme.ButtonPadding
import com.evg.ui.theme.DailyPlannerTheme
import com.evg.ui.theme.HorizontalPadding
import com.evg.ui.theme.VerticalPadding

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.TaskCreationScreen(
    modifier: Modifier,
    animatedVisibilityScope: AnimatedVisibilityScope,
    dispatch: (TaskCreationAction) -> Unit,
    onToDoListScreen: () -> Unit,
) {
    val context = LocalContext.current
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var dateStartMillis by remember { mutableLongStateOf(System.currentTimeMillis()) }
    var dateFinishMillis by remember { mutableLongStateOf(System.currentTimeMillis() + 60 * 1000L) }

    val titleSpace = 8.dp
    val fieldSpace = 18.dp

    Column(
        modifier = modifier
            .padding(
                horizontal = HorizontalPadding,
                vertical = VerticalPadding,
            )
            .sharedBounds(
                sharedContentState = rememberSharedContentState(
                    key = "FAB_EXPLODE_BOUNDS_KEY"
                ),
                animatedVisibilityScope = animatedVisibilityScope,
            )
            .verticalScroll(rememberScrollState()),
    ) {
        Text(
            text = stringResource(R.string.task_name),
            color = AppTheme.colors.text,
            style = AppTheme.typography.heading2,
        )
        Spacer(modifier = Modifier.height(titleSpace))

        CustomTextField(
            title = stringResource(R.string.enter_task_name),
            text = name,
            onTextChange = { name = it },
            maxLines = 1,
        )
        Spacer(modifier = Modifier.height(fieldSpace))

        Text(
            text = stringResource(R.string.task_description),
            color = AppTheme.colors.text,
            style = AppTheme.typography.heading2,
        )
        Spacer(modifier = Modifier.height(titleSpace))


        CustomTextField(
            modifier = Modifier
                .height(120.dp),
            title = stringResource(R.string.enter_task_description),
            text = description,
            onTextChange = { description = it },
            maxLines = 6,
        )
        Spacer(modifier = Modifier.height(fieldSpace))

        Text(
            text = stringResource(R.string.task_time),
            color = AppTheme.colors.text,
            style = AppTheme.typography.heading2,
        )
        Spacer(modifier = Modifier.height(titleSpace))

        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(BorderRadius))
                .background(AppTheme.colors.tileBackground)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            CustomDateTimeSelection(
                modifier = Modifier
                    .weight(1f),
                currentTimestamp = dateStartMillis,
                onTimeSelected = { dateStartMillis = it },
            )

            Icon(
                modifier = Modifier
                    .size(24.dp)
                    .graphicsLayer(scaleX = -1f),
                painter = painterResource(R.drawable.arrow_back),
                contentDescription = null,
                tint = AppTheme.colors.text,
            )

            CustomDateTimeSelection(
                modifier = Modifier
                    .weight(1f),
                currentTimestamp = dateFinishMillis,
                onTimeSelected = { dateFinishMillis = it },
            )
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        RoundedButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(ButtonPadding),
            backgroundColor = AppTheme.colors.secondary,
            icon = painterResource(R.drawable.save),
            iconColor = AppTheme.colors.primary,
            onClick = {
                if (name.isBlank()) {
                    Toast.makeText(context, context.getString(R.string.enter_task_name), Toast.LENGTH_SHORT).show()
                } else if (description.isBlank()) {
                    Toast.makeText(context, context.getString(R.string.enter_task_description), Toast.LENGTH_SHORT).show()
                } else if (dateStartMillis >= dateFinishMillis) {
                    Toast.makeText(context, context.getString(R.string.time_start_is_greater), Toast.LENGTH_SHORT).show()
                } else {
                    dispatch(TaskCreationAction.AddTask(
                        task = Task(
                            name = name,
                            description = description,
                            dateStart = dateStartMillis,
                            dateFinish = dateFinishMillis,
                        )
                    ))
                    Toast.makeText(context, context.getString(R.string.task_is_saved), Toast.LENGTH_SHORT).show()
                    onToDoListScreen()
                }
            },
        )
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Preview
@Composable
private fun TaskCreationScreenPreview() {
    DailyPlannerTheme(darkTheme = true) {
        Surface(color = AppTheme.colors.background) {
            SharedTransitionLayout {
                AnimatedVisibility(visibleState = MutableTransitionState(true)) {
                    TaskCreationScreen(
                        modifier = Modifier,
                        animatedVisibilityScope = this,
                        dispatch = {},
                        onToDoListScreen = {},
                    )
                }
            }
        }
    }
}