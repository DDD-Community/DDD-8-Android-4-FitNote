package com.dogandpigs.fitnote.presentation.lesson.memberlessonlist

import com.dogandpigs.fitnote.presentation.navigation.NavRoute

object MemberLessonListNavRoute : NavRoute {
    override val path: String
        get() = "route_member_lesson_list"

    const val memberId = "member_id"

    val route: String = "$path/{$memberId}"

    fun getPath(memberId: Long): String =
        "$path/$memberId"
}
