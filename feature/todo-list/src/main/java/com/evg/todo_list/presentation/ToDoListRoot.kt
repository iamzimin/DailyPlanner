package com.evg.todo_list.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import com.evg.todo_list.presentation.mvi.ToDoListSideEffect
import com.evg.todo_list.presentation.mvi.ToDoListViewModel

@Composable
fun ToDoListRoot(
    viewModel: ToDoListViewModel = hiltViewModel(),
    onTaskCreationScreen: () -> Unit,
    onTaskDescriptionScreen: () -> Unit,
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
        state = viewModel.collectAsState().value,
        dispatch = viewModel::dispatch,
        onTaskDescriptionScreen = onTaskCreationScreen,
        onTaskCreationScreen = onTaskDescriptionScreen,
    )
}