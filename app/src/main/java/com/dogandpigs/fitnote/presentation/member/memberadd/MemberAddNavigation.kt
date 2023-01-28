package com.dogandpigs.fitnote.presentation.member.memberadd

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.dogandpigs.fitnote.presentation.login.LoginScreen
import com.dogandpigs.fitnote.presentation.navigation.ARGUMENT_EMAIL
import com.dogandpigs.fitnote.presentation.navigation.ARGUMENT_REGISTRATION

fun NavGraphBuilder.addMemberAdd(
    route: String,
    popBackStack: () -> Unit,
    navigateToMemberListWithRegistration: (Boolean) -> Unit,
) {
    composable(route = route) {
        MemberAddScreen(
            popBackStack = popBackStack,
            navigateToMemberListWithRegistration = navigateToMemberListWithRegistration,
        )
    }
}
