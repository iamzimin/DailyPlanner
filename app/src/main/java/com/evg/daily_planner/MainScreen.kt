package com.evg.daily_planner

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.evg.daily_planner.navigation.Route
import com.evg.todo_list.presentation.ToDoListRoot
import com.evg.ui.theme.AppTheme

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val startDestination = Route.ToDoList

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = AppTheme.colors.background,
    ) { paddingValues ->
        NavHost(
            modifier = Modifier.fillMaxSize().padding(paddingValues),
            navController = navController,
            startDestination = startDestination,
        ) {
            composable<Route.ToDoList> {
                ToDoListRoot(
                    /*modifier = Modifier.fillMaxSize().padding(paddingValues),
                    onScreen = {
                        navController.navigate(route = Route.Screen)
                    }*/
                )
            }
            composable<Route.TaskDescription> {

            }
            composable<Route.TaskCreation> {

            }
        }
    }
}