package com.evg.todo_list.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.evg.todo_list.presentation.mvi.ToDoListState
import com.evg.todo_list.presentation.mvi.ToDoListAction
import com.evg.ui.theme.DailyPlannerTheme

@Composable
fun ToDoListScreen(
    state: ToDoListState,
    dispatch: (ToDoListAction) -> Unit,
) {

}

@Preview
@Composable
private fun ToDoListScreenPreview() {
    DailyPlannerTheme {
        ToDoListScreen(
            state = ToDoListState(),
            dispatch = {},
        )
    }
}
