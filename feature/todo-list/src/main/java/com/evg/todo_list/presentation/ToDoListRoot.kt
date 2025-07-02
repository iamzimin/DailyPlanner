package com.evg.todo_list.presentation

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.evg.todo_list.domain.model.Task
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import com.evg.todo_list.presentation.mvi.ToDoListSideEffect
import com.evg.todo_list.presentation.mvi.ToDoListViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.ToDoListRoot(
    viewModel: ToDoListViewModel = hiltViewModel(),
    modifier: Modifier,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onTaskCreationScreen: () -> Unit,
    onTaskDescriptionScreen: (Task) -> Unit,
) {
    /*viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            is ToDoListSideEffect.FirstClass -> {
                when (sideEffect.paramOne) {
                    "" -> {}
                }
            }
            ToDoListSideEffect.FirstObject -> {}
        }
    }*/

    ToDoListScreen(
        modifier = modifier,
        animatedVisibilityScope = animatedVisibilityScope,
        state = viewModel.collectAsState().value,
        dispatch = viewModel::dispatch,
        onTaskCreationScreen = onTaskCreationScreen,
        onTaskDescriptionScreen = onTaskDescriptionScreen,
    )
}