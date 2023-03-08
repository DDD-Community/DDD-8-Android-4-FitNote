package com.dogandpigs.fitnote.presentation.member.memberedit

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.dogandpigs.fitnote.presentation.navigation.ARGUMENT_MEMBER_ID

fun NavGraphBuilder.addMemberEdit(
    route: String,
    popBackStack: () -> Unit,
    navigateToMemberDetail: (Long) -> Unit,
) {
    val argument = ARGUMENT_MEMBER_ID

    composable(
        route = route,
        arguments = listOf(navArgument(argument) { type = NavType.LongType }),
    ) { backStackEntry ->

        val memberId = backStackEntry.arguments?.getLong(argument, 0L) ?: 0L

        MemberEditScreen(
            memberId = memberId,
            popBackStack = popBackStack,
            navigateToMemberDetail = navigateToMemberDetail,
        )
    }
}
