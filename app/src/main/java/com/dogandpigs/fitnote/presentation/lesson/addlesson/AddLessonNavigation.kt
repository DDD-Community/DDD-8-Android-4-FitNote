package com.dogandpigs.fitnote.presentation.lesson.addlesson

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.addAddLesson(
    route: String,
    popBackStack: () -> Unit,
    navigateToLoadLesson: () -> Unit,
    navigateToAddExercise: () -> Unit,
) {
    composable(route = route) {
        AddLessonScreen(
            popBackStack = popBackStack,
            navigateToLoadLesson = navigateToLoadLesson,
            navigateToAddExercise = navigateToAddExercise,
        )
    }
}
