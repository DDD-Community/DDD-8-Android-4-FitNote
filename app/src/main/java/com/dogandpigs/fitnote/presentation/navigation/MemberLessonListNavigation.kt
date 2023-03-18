package com.dogandpigs.fitnote.presentation.navigation

import androidx.navigation.NavHostController
import com.dogandpigs.fitnote.presentation.lesson.memberlessonlist.MemberLessonListNavRoute

internal fun NavHostController.navigateToMemberLessonListRoute(
    memberId: Int,
) {
    this.navigate(MemberLessonListNavRoute.getPath(memberId)) {
        popUpTo(MemberLessonListNavRoute.route) { inclusive = true }
    }
}
