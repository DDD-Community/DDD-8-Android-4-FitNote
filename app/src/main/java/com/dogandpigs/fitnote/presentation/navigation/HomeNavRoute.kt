package com.dogandpigs.fitnote.presentation.navigation

import androidx.navigation.NavHostController
import com.dogandpigs.fitnote.presentation.member.memberlist.MemberListNavRoute

internal object HomeNavRoute : NavRoute {
    override val path: String
        get() = MemberListNavRoute.path
}

internal fun NavHostController.navigateToHome() {
    this.navigate(HomeNavRoute.path) {
        popUpTo(this@navigateToHome.graph.id) {
            inclusive = true
        }
    }
}
