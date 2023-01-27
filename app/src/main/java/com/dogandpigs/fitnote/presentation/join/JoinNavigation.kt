package com.dogandpigs.fitnote.presentation.join

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.addJoin(
    route: String,
    popBackStack: () -> Unit,
    navigateToLogin: (String) -> Unit,
) {
    composable(route = route) {
        JoinScreen(
            popBackStack = popBackStack,
            navigateToLogin = navigateToLogin,
        )
    }
}
