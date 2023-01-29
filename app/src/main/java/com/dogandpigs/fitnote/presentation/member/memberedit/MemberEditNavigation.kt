package com.dogandpigs.fitnote.presentation.member.memberedit

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.addMemberEdit(
    route: String,
    popBackStack: () -> Unit,
    navigateToMemberListWithRegistration: (Boolean) -> Unit,
) {
    composable(route = route) {
        MemberEditScreen(
            popBackStack = popBackStack,
            navigateToMemberListWithRegistration = navigateToMemberListWithRegistration,
        )
    }
}
