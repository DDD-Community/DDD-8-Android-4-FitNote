package com.dogandpigs.fitnote.presentation.setting

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.addSettingScreen(
    route: String,
    popBackStack: () -> Unit,
    navigateToSplash: () -> Unit,
) {
    composable(route = route) {
        SettingScreen(
            popBackStack = popBackStack,
            navigateToSplash = navigateToSplash,
        )
    }
}
