package com.dogandpigs.fitnote.presentation.member.memberadd

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.dogandpigs.fitnote.presentation.MainViewModel

fun NavGraphBuilder.addMemberAdd(
    route: String,
    mainViewModel: MainViewModel,
    navigateToHome: () -> Unit,
) {
    composable(route = route) {
        MemberAddScreen(
            navigateToHome = navigateToHome,
            mainViewModel = mainViewModel,
        )
    }
}
