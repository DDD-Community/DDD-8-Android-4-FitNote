package com.dogandpigs.fitnote.presentation.member.memberadd

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.addMemberAdd(
    route: String,
    navigateToHome: () -> Unit,
    navigateToMemberListWithRegistration: (Boolean) -> Unit,
) {
    composable(route = route) {
        MemberAddScreen(
            navigateToHome = navigateToHome,
            navigateToMemberListWithRegistration = navigateToMemberListWithRegistration,
        )
    }
}
