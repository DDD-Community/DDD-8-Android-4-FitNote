package com.dogandpigs.fitnote.presentation.member.memberdetail

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.dogandpigs.fitnote.presentation.navigation.ARGUMENT_MEMBER_ID

fun NavGraphBuilder.addMemberDetail(
    route: String,
    popBackStack: () -> Unit,
    navigateToMemberEdit: (Long) -> Unit,
    navigateToMemberLessonList: (Long) -> Unit,
) {
    val argument = ARGUMENT_MEMBER_ID

    composable(
        route = route,
        arguments = listOf(navArgument(argument) { type = NavType.LongType }),
    ) { backStackEntry ->

        val memberId = backStackEntry.arguments?.getLong(argument, 0L) ?: 0L

        MemberDetailScreen(
            memberId = memberId,
            popBackStack = popBackStack,
            navigateToMemberEdit = navigateToMemberEdit,
            navigateToMemberLessonList = navigateToMemberLessonList,
        )
    }
}
