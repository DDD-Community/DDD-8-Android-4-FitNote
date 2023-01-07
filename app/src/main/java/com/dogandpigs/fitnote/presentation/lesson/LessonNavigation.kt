package com.dogandpigs.fitnote.presentation.lesson

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.addLesson(
    route: String,
    popBackStack: () -> Unit,
    navigateToAddLesson: () -> Unit,
    navigateToSetting: () -> Unit,
) {
    composable(route = route) {
        LessonScreen(
            popBackStack = popBackStack,
            navigateToAddLesson = navigateToAddLesson,
            navigateToSetting = navigateToSetting,
        )
    }
}
