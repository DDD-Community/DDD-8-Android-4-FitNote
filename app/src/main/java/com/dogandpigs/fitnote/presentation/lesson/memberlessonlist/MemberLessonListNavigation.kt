package com.dogandpigs.fitnote.presentation.lesson.memberlessonlist

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.dogandpigs.fitnote.presentation.navigation.ARGUMENT_MEMBER_ID

fun NavGraphBuilder.addMemberLessonListScreen(
    route: String,
    popBackStack: () -> Unit,
    navigateToAddLesson: (Long) -> Unit,
    navigateToMemberLesson: (Long, Int) -> Unit,
    navigateToShare: (Long, Int) -> Unit,
) {
    val argument = ARGUMENT_MEMBER_ID

    composable(
        route = route,
        arguments = listOf(navArgument(argument) { type = NavType.LongType }),
    ) { backStackEntry ->

        val memberId = backStackEntry.arguments?.getLong(argument, 0L) ?: 0L

        MemberLessonListScreen(
            memberId = memberId,
            popBackStack = popBackStack,
            navigateToAddLesson = navigateToAddLesson,
            navigateToMemberLesson = navigateToMemberLesson,
            navigateToShare = navigateToShare,
        )
    }
}
