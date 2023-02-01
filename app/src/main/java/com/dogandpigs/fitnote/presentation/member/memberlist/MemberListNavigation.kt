package com.dogandpigs.fitnote.presentation.member.memberlist

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.dogandpigs.fitnote.presentation.navigation.ARGUMENT_REGISTRATION

fun NavGraphBuilder.addMemberList(
    route: String,
    navigateToMemberDetail: () -> Unit,
    navigateToMemberAdd: () -> Unit,
    navigateToLesson: () -> Unit,
    navigateToSetting: () -> Unit
) {
    composable(route = route) {
        MemberListScreen(
            navigateToMemberDetail = navigateToMemberDetail,
            navigateToMemberAdd = navigateToMemberAdd,
            navigateToMemberLessonList = navigateToLesson,
            navigateToSetting = navigateToSetting
        )
    }
}

internal fun NavGraphBuilder.addMemberListWithRegistration(
    route: String,
    navigateToMemberDetail: () -> Unit,
    navigateToMemberAdd: () -> Unit,
    navigateToLesson: () -> Unit,
    navigateToSetting: () -> Unit,
) {
    val argument = ARGUMENT_REGISTRATION

    composable(
        route = route,
        arguments = listOf(navArgument(argument) { type = NavType.BoolType }),
    ) { backStackEntry ->
        val registration = backStackEntry.arguments?.getBoolean(argument, false) ?: false

        MemberListScreen(
            registration = registration,
            navigateToMemberDetail = navigateToMemberDetail,
            navigateToMemberAdd = navigateToMemberAdd,
            navigateToMemberLessonList = navigateToLesson,
            navigateToSetting = navigateToSetting,
        )
    }
}
