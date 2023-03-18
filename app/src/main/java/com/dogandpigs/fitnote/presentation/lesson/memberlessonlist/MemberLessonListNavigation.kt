package com.dogandpigs.fitnote.presentation.lesson.memberlessonlist

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.dogandpigs.fitnote.presentation.navigation.ARGUMENT_MEMBER_ID

fun NavGraphBuilder.addMemberLessonListScreen(
    route: String,
    popBackStack: () -> Unit,
    navigateToAddLesson: (Int) -> Unit,
    navigateToMemberLesson: (Int, Int) -> Unit,
    navigateToShare: (Int, Int) -> Unit,
    navigateToEdit: (memberId: Int, lessonDate: Int) -> Unit,
) {
    val argument = ARGUMENT_MEMBER_ID

    composable(
        route = route,
        arguments = listOf(
            navArgument(argument) {
                type = NavType.IntType
                nullable = false
                defaultValue = 0
            },
        )
    ) { backStackEntry ->

        val memberId = backStackEntry.arguments?.getInt(argument, 0) ?: 0

        MemberLessonListScreen(
            memberId = memberId,
            popBackStack = popBackStack,
            navigateToAddLesson = navigateToAddLesson,
            navigateToMemberLesson = navigateToMemberLesson,
            navigateToShare = navigateToShare,
            navigateToEdit = navigateToEdit,
        )
    }
}
