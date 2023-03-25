package com.dogandpigs.fitnote.presentation.member.memberlist

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.addMemberList(
    route: String,
    navigateToMemberDetail: (Int) -> Unit,
    navigateToMemberAdd: () -> Unit,
    navigateToMemberLessonList: (Int) -> Unit,
    navigateToSetting: () -> Unit
) {
    composable(route = route) {
        MemberListScreen(
            navigateToMemberDetail = navigateToMemberDetail,
            navigateToMemberAdd = navigateToMemberAdd,
            navigateToMemberLessonList = navigateToMemberLessonList,
            navigateToSetting = navigateToSetting
        )
    }
}
