package com.dogandpigs.fitnote.presentation.join

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.addJoin(
    route: String,
    popBackStack: () -> Unit,
) {
    composable(route = route) {
        JoinRoute(
            popBackStack = popBackStack
        )
    }
}

@Composable
private fun JoinRoute(
    viewModel: JoinViewModel = hiltViewModel(),
    popBackStack: () -> Unit
) {
    JoinScreen(
        viewModel = viewModel,
        popBackStack = popBackStack
    )
}
