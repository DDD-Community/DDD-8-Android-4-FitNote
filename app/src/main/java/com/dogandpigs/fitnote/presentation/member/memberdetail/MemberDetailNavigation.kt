package com.dogandpigs.fitnote.presentation.member.memberdetail

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.dogandpigs.fitnote.presentation.navigation.ARGUMENT_MEMBER_ID

fun NavGraphBuilder.addMemberDetail(
    route: String,
    popBackStack: () -> Unit,
    navigateToMemberEdit: (Int) -> Unit,
    navigateToMemberLessonList: (Int) -> Unit,
) {
    val argument = ARGUMENT_MEMBER_ID

    composable(
        route = route,
        arguments = listOf(navArgument(argument) { type = NavType.IntType }),
    ) { backStackEntry ->

        val memberId = backStackEntry.arguments?.getInt(argument, 0) ?: 0

        MemberDetailScreen(
            memberId = memberId,
            popBackStack = popBackStack,
            navigateToMemberEdit = navigateToMemberEdit,
            navigateToMemberLessonList = navigateToMemberLessonList,
        )
    }
}
