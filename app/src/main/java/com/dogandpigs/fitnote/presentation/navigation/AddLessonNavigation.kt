package com.dogandpigs.fitnote.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.dogandpigs.fitnote.presentation.MainViewModel
import com.dogandpigs.fitnote.presentation.lesson.LessonMode
import com.dogandpigs.fitnote.presentation.lesson.addlesson.AddLessonNavRoute
import com.dogandpigs.fitnote.presentation.lesson.addlesson.AddLessonScreen
import com.dogandpigs.fitnote.presentation.lesson.loadlesson.LoadLessonNavRoute

fun NavGraphBuilder.addAddLessonScreen(
    route: String,
    mainViewModel: MainViewModel,
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
            navArgument(AddLessonNavRoute.mode) {
                type = NavType.IntType
                nullable = false
                defaultValue = LessonMode.UNKNOWN.value
            },
        )
    ) {
        val memberId = it.arguments?.getInt(AddLessonNavRoute.memberId, 0) ?: 0
        val lessonId = it.arguments?.getInt(AddLessonNavRoute.lessonId, 0) ?: 0
        val mode = LessonMode.values().firstOrNull { lessonMode ->
            lessonMode.value == it.arguments?.getInt(
                AddLessonNavRoute.mode,
                LessonMode.UNKNOWN.value
            )
        } ?: LessonMode.UNKNOWN

        AddLessonScreen(
            mainViewModel = mainViewModel,
            memberId = memberId,
            lessonId = lessonId,
            mode = mode,
            popBackStack = popBackStack,
            navigateToLoadLesson = navigateToLoadLesson,
            navigateToMemberLessonList = navigateToMemberLessonList,
        )
    }
}

internal fun NavHostController.navigateToAddLesson(
    memberId: Int,
    lessonId: Int = 0,
    mode: LessonMode = LessonMode.ADD,
) {
    this.navigate(AddLessonNavRoute.query(memberId, lessonId, mode)) {
        launchSingleTop = true
        popUpTo(LoadLessonNavRoute.path) { inclusive = true }
    }
}
