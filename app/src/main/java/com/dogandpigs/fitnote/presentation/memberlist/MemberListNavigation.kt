package com.dogandpigs.fitnote.presentation.memberlist

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.addMemberList(
    route: String,
    popBackStack: () -> Unit,
    navigateToMemberDetail: () -> Unit,
    navigateToAddMember: () -> Unit,
    navigateToLesson: () -> Unit,
    navigateToSetting: () -> Unit,
) {
    composable(route = route) {
        MemberListScreen(
            popBackStack = popBackStack,
            navigateToMemberDetail = navigateToMemberDetail,
            navigateToAddMember = navigateToAddMember,
            navigateToLesson = navigateToLesson,
            navigateToSetting = navigateToSetting,
        )
    }
}
