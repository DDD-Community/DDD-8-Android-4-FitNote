package com.dogandpigs.fitnote.presentation.navigation

import androidx.navigation.NavHostController
import com.dogandpigs.fitnote.presentation.member.memberlist.MemberListNavRoute

internal object HomeNavRoute : NavRoute {
    override val route: String
        get() = MemberListNavRoute.route
}

internal fun NavHostController.navigateToHome() {
    this.navigate(HomeNavRoute.route) {
        popUpTo(this@navigateToHome.graph.id) {
            inclusive = true
        }
    }
}
