package com.dogandpigs.fitnote.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.dogandpigs.fitnote.presentation.lesson.addlesson.AddLessonNavRoute
import com.dogandpigs.fitnote.presentation.lesson.loadlesson.LoadLessonNavRoute
import com.dogandpigs.fitnote.presentation.lesson.addlesson.AddLessonScreen

fun NavGraphBuilder.addAddLessonScreen(
    route: String,
    popBackStack: () -> Unit,
    navigateToLoadLesson: () -> Unit,
    navigateToMemberLessonList: (Int) -> Unit,
) {
    composable(
        route = route,
        arguments = listOf(
            navArgument(AddLessonNavRoute.memberId) {
                type = NavType.IntType
                nullable = false
                defaultValue = 0
            },
            navArgument(AddLessonNavRoute.lessonId) {
                type = NavType.IntType
                nullable = false
                defaultValue = 0
            },
        )
    ) {
        val memberId = it.arguments?.getInt(AddLessonNavRoute.memberId, 0) ?: 0
        val lessonId = it.arguments?.getInt(AddLessonNavRoute.lessonId, 0) ?: 0

        AddLessonScreen(
            memberId = memberId,
            lessonId = lessonId,
            popBackStack = popBackStack,
            navigateToLoadLesson = navigateToLoadLesson,
            navigateToMemberLessonList = navigateToMemberLessonList,
        )
    }
}

internal fun NavHostController.navigateToAddLesson(memberId: Int, lessonId: Int = 0) {
    this.navigate(AddLessonNavRoute.query(memberId, lessonId)) {
        launchSingleTop = true
        popUpTo(LoadLessonNavRoute.path) { inclusive = true }
    }
}
