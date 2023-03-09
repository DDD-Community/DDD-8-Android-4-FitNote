package com.dogandpigs.fitnote.presentation.lesson.memberlessonlist

import com.dogandpigs.fitnote.presentation.navigation.NavRoute

object MemberLessonListNavRoute : NavRoute {
    override val route: String
        get() = "route_member_lesson_list"

    const val memberId = "member_id"

    val path: String = "$route/{$memberId}"

    fun getPath(memberId: Long): String =
        "$route/$memberId"
}
