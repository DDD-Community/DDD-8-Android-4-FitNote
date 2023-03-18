package com.dogandpigs.fitnote.presentation.lesson.addlesson

import com.dogandpigs.fitnote.presentation.navigation.NavRoute

object AddLessonNavRoute : NavRoute {
    override val path: String
        get() = "route_add_lesson"

    const val memberId = "member_id"
    const val lessonId = "lesson_id"

    val route: String = "$path/{$memberId}/{$lessonId}"

    fun query(memberId: Int): String =
        "$path/$memberId"

    fun query(memberId: Int, lessonId: Int): String =
        "$path/$memberId/$lessonId"
}
