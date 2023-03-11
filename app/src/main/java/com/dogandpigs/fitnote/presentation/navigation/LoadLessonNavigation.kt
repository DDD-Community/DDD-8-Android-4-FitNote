package com.dogandpigs.fitnote.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.dogandpigs.fitnote.presentation.lesson.loadlesson.LoadLessonNavRoute
import com.dogandpigs.fitnote.presentation.lesson.loadlesson.LoadLessonScreen

fun NavGraphBuilder.addLoadLesson(
    route: String,
    popBackStack: () -> Unit
) {
    composable(route = route) {
        LoadLessonScreen(
            popBackStack = popBackStack
        )
    }
}

internal fun NavHostController.navigateToLoadLesson() {
    this.navigate(LoadLessonNavRoute.route)
}
