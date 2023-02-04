package com.dogandpigs.fitnote.presentation.lesson.memberlessonlist

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.addMemberLessonList(
    route: String,
    popBackStack: () -> Unit,
    navigateToAddLesson: () -> Unit,
) {
    composable(route = route) {
        MemberLessonListScreen(
            popBackStack = popBackStack,
            navigateToAddLesson = navigateToAddLesson,
        )
    }
}
