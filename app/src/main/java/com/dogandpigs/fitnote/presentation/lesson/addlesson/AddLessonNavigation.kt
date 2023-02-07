package com.dogandpigs.fitnote.presentation.lesson.addlesson

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.dogandpigs.fitnote.presentation.navigation.ARGUMENT_MEMBER_ID

fun NavGraphBuilder.addAddLesson(
    route: String,
    popBackStack: () -> Unit,
    navigateToLoadLesson: () -> Unit,
    navigateToAddExercise: () -> Unit,
) {
    val argument = ARGUMENT_MEMBER_ID
    composable(
        route = route,
        arguments = listOf(navArgument(argument) { type = NavType.IntType })
    ) {
        val memberId = it.arguments?.getInt(argument, 0) ?: 0
        AddLessonScreen(
            memberId = memberId,
            popBackStack = popBackStack,
            navigateToLoadLesson = navigateToLoadLesson,
            navigateToAddExercise = navigateToAddExercise,
        )
    }
}
