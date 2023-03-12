package com.dogandpigs.fitnote.presentation.lesson.addlesson

import com.dogandpigs.fitnote.presentation.navigation.NavRoute

object AddLessonNavRoute : NavRoute {
    override val route: String
        get() = "route_add_lesson"

    const val memberId = "member_id"
    const val lessonId = "lesson_id"

    val path: String = "$route/{$memberId}/{$lessonId}"

    fun query(memberId: Int): String =
        "$route/$memberId"

    fun query(memberId: Int, lessonId: Int): String =
        "$route/$memberId/$lessonId"
}
