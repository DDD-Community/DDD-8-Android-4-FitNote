package com.dogandpigs.fitnote.presentation.member.memberdetail

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.addMemberDetail(
    route: String,
    popBackStack: () -> Unit,
    navigateToMemberEdit: (Int) -> Unit,
    navigateToMemberLessonList: (Int) -> Unit,
) {
    composable(route = route) {
        MemberDetailScreen(
            popBackStack = popBackStack,
            navigateToMemberEdit = navigateToMemberEdit,
            navigateToMemberLessonList = navigateToMemberLessonList,
        )
    }
}
