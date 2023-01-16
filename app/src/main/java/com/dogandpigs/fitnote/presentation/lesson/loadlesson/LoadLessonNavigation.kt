package com.dogandpigs.fitnote.presentation.lesson.loadlesson

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.dogandpigs.fitnote.presentation.lesson.addlesson.AddLessonScreen

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