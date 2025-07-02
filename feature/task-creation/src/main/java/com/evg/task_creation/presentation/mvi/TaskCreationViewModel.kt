package com.evg.task_creation.presentation.mvi

import androidx.lifecycle.ViewModel
import com.evg.task_creation.domain.model.Task
import com.evg.task_creation.domain.repository.TaskCreationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class TaskCreationViewModel @Inject constructor(
    private val taskCreationRepository: TaskCreationRepository,
) : ContainerHost<TaskCreationState, TaskCreationSideEffect>, ViewModel() {
    override val container = container<TaskCreationState, TaskCreationSideEffect>(TaskCreationState())

    fun dispatch(action: TaskCreationAction) {
        when (action) {
            is TaskCreationAction.AddTask -> addTask(task = action.task)
        }
    }

    private fun addTask(task: Task) = intent {
        taskCreationRepository.insertTask(task = task)
    }
}