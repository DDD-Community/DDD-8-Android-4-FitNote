package com.dogandpigs.fitnote.presentation.share

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.dogandpigs.fitnote.presentation.navigation.ARGUMENT_LESSON_DATE
import com.dogandpigs.fitnote.presentation.navigation.ARGUMENT_MEMBER_ID

fun NavGraphBuilder.addShare(
    route: String,
) {
    composable(
        route = route,
        arguments = listOf(
            navArgument(ARGUMENT_MEMBER_ID) { type = NavType.IntType },
            navArgument(ARGUMENT_LESSON_DATE) { type = NavType.IntType },
        ),
    ) { backStackEntry ->

        val memberId = backStackEntry.arguments?.getInt(ARGUMENT_MEMBER_ID, 0) ?: 0
        val lessonDate = backStackEntry.arguments?.getInt(ARGUMENT_LESSON_DATE, 0) ?: 0

        ShareScreen(
            memberId = memberId,
            lessonDate = lessonDate,
        )
    }
}
