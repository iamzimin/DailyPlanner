package com.evg.todo_list.presentation.mvi

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class ToDoListViewModel @Inject constructor(

) : ContainerHost<ToDoListState, ToDoListSideEffect>, ViewModel() {
    override val container = container<ToDoListState, ToDoListSideEffect>(ToDoListState())

    fun dispatch(action: ToDoListAction) {
        when (action) {
            is ToDoListAction.FirstClass -> test()
            is ToDoListAction.SecondObject -> test()
        }
    }

    private fun test() = intent {
        //reduce { state.copy(variable = true) }
    }
}