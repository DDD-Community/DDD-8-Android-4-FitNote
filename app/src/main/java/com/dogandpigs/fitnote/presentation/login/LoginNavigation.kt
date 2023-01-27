package com.dogandpigs.fitnote.presentation.login

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.dogandpigs.fitnote.presentation.navigation.NavRoutes.Companion.ARGUMENT_EMAIL

internal fun NavGraphBuilder.addLogin(
    route: String,
    navigateToHome: () -> Unit,
) {
    composable(route = route) {
        LoginScreen(
            navigateToHome = navigateToHome,
        )
    }
}

internal fun NavGraphBuilder.addLoginWithEmail(
    route: String,
    navigateToHome: () -> Unit,
) {
    val argument = ARGUMENT_EMAIL

    composable(
        route = route,
        arguments = listOf(navArgument(argument) { type = NavType.StringType })
    ) { backStackEntry ->
        val email = backStackEntry.arguments?.getString(argument)

        LoginScreen(
            email = email,
            navigateToHome = navigateToHome,
        )
    }
}
