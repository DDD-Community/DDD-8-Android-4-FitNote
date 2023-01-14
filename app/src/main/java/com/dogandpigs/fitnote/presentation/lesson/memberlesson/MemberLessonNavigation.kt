package com.dogandpigs.fitnote.presentation.lesson.memberlesson

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.addMemberLesson(
    route: String,
    popBackStack: () -> Unit,
    navigateToAddLesson: () -> Unit,
    navigateToSetting: () -> Unit,
) {
    composable(route = route) {
        MemberLessonScreen(
            popBackStack = popBackStack,
            navigateToAddLesson = navigateToAddLesson,
            navigateToSetting = navigateToSetting,
        )
    }
}
