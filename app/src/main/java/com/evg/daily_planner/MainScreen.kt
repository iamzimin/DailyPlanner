package com.evg.daily_planner

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.evg.daily_planner.mapper.toDescriptionTask
import com.evg.daily_planner.navigation.Route
import com.evg.daily_planner.mapper.TaskDescriptionNavType
import com.evg.daily_planner.scaffold.DailyPlannerScaffold
import com.evg.task_creation.presentation.TaskCreationRoot
import com.evg.task_description.presentation.TaskDescriptionRoot
import com.evg.task_description.presentation.model.TaskDescription
import com.evg.todo_list.presentation.ToDoListRoot
import com.evg.ui.theme.AppTheme
import kotlin.reflect.typeOf

@OptIn(ExperimentalSharedTransitionApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize().imePadding(),
        topBar = { TopBar(navController) },
        containerColor = AppTheme.colors.background,
    ) {
        SharedTransitionLayout {
            NavHost(
                navController = navController,
                startDestination = Route.ToDoList,
                modifier = Modifier.background(AppTheme.colors.background),
            ) {
                composable<Route.ToDoList> { entry ->
                    DailyPlannerScaffold { paddingValues ->
                        ToDoListRoot(
                            modifier = Modifier.fillMaxSize().padding(paddingValues),
                            animatedVisibilityScope = this,
                            onTaskCreationScreen = {
                                navController.navigate(route = Route.TaskCreation)
                            },
                            onTaskDescriptionScreen = { task ->
                                navController.navigate(route = Route.TaskDescription(
                                    task = task.toDescriptionTask()
                                ))
                            },
                        )
                    }
                }
                composable<Route.TaskDescription>(
                    typeMap = mapOf(typeOf<TaskDescription>() to TaskDescriptionNavType())
                ) { entry ->
                    val route = entry.toRoute<Route.TaskDescription>()

                    DailyPlannerScaffold(
                        modifier = Modifier.padding(top = topNavPadding),
                    ) { paddingValues ->
                        TaskDescriptionRoot(
                            modifier = Modifier.fillMaxSize().padding(paddingValues),
                            taskDescription = route.task,
                        )
                    }
                }
                composable<Route.TaskCreation> {
                    DailyPlannerScaffold(
                        modifier = Modifier.padding(top = topNavPadding),
                    ) { paddingValues ->
                        TaskCreationRoot(
                            modifier = Modifier.fillMaxSize().padding(paddingValues),
                            animatedVisibilityScope = this,
                            onToDoListScreen = {
                                navController.navigate(route = Route.ToDoList) {
                                    popUpTo<Route.TaskCreation> {
                                        inclusive = true
                                    }
                                    popUpTo<Route.ToDoList> {
                                        inclusive = true
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}