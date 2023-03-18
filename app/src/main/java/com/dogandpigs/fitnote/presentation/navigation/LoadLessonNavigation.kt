package com.dogandpigs.fitnote.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.dogandpigs.fitnote.presentation.lesson.loadlesson.LoadLessonNavRoute
import com.dogandpigs.fitnote.presentation.lesson.loadlesson.LoadLessonScreen

fun NavGraphBuilder.addLoadLesson(
    route: String,
    popBackStack: () -> Unit,
    navigateToAddLessonWithLoad: (Int, Int) -> Unit,
) {
    composable(route = route) {
        LoadLessonScreen(
            popBackStack = popBackStack,
            navigateToAddLessonWithLoad = navigateToAddLessonWithLoad,
        )
    }
}

internal fun NavHostController.navigateToLoadLesson() {
    this.navigate(LoadLessonNavRoute.path) {
        launchSingleTop = true
    }
}
