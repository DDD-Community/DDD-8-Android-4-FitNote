package com.dogandpigs.fitnote.presentation.navigation

import androidx.navigation.NavHostController
import com.dogandpigs.fitnote.presentation.lesson.memberlessonlist.MemberLessonListNavRoute

internal fun NavHostController.navigateToMemberLessonListRoute(
    memberId: Long,
) {
    this.navigate(MemberLessonListNavRoute.getPath(memberId)) {
        popUpTo(MemberLessonListNavRoute.path) { inclusive = true }
    }
}
