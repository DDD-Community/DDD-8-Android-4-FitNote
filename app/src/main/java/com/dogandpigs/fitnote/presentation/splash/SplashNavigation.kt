package com.dogandpigs.fitnote.presentation.splash

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

internal fun NavGraphBuilder.addSplash(
    route: String,
    navigateToHome: () -> Unit,
    navigateToJoin: () -> Unit,
    navigateToLogin: () -> Unit,
    navigateToLesson: () -> Unit,
    navigateToMemberList: () -> Unit,
    navigateToMemberLesson: () -> Unit,
) {
    composable(route = route) {
        SplashRoute(
            navigateToHome = navigateToHome,
            navigateToJoin = navigateToJoin,
            navigateToLogin = navigateToLogin,
            navigateToLesson = navigateToLesson,
            navigateToMemberList = navigateToMemberList,
            navigateToMemberLesson = navigateToMemberLesson,
        )
    }
}

@Composable
private fun SplashRoute(
    viewModel: SplashViewModel = hiltViewModel(),
    navigateToHome: () -> Unit,
    navigateToJoin: () -> Unit,
    navigateToLogin: () -> Unit,
    navigateToLesson: () -> Unit,
    navigateToMemberList: () -> Unit,
    navigateToMemberLesson: () -> Unit,
) {
    SplashScreen(
        viewModel = viewModel,
        navigateToHome = navigateToHome,
        navigateToJoin,
        navigateToLogin,
        navigateToLesson = navigateToLesson,
        navigateToMemberList = navigateToMemberList,
        navigateToMemberLesson = navigateToMemberLesson,
    )
}
