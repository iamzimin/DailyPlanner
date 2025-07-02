package com.evg.task_creation.presentation

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.evg.task_creation.presentation.mvi.TaskCreationViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.TaskCreationRoot(
    viewModel: TaskCreationViewModel = hiltViewModel(),
    modifier: Modifier,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onToDoListScreen: () -> Unit,
) {
    TaskCreationScreen(
        modifier = modifier,
        animatedVisibilityScope = animatedVisibilityScope,
        dispatch = viewModel::dispatch,
        onToDoListScreen = onToDoListScreen,
    )
}