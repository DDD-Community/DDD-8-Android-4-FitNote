package com.dogandpigs.fitnote.presentation.lesson.addlesson

import com.dogandpigs.fitnote.presentation.navigation.NavRoute

object AddLessonNavRoute : NavRoute {
    override val path: String
        get() = "route_add_lesson"

    const val memberId = "member_id"
    const val lessonId = "lesson_id"
    const val mode = "mode"
    const val addMode = 0
    const val editMode = 1

    val route: String = "$path/{$memberId}/{$lessonId}/{$mode}"

    fun query(
        memberId: Int,
        lessonId: Int = 0,
        mode: Int = addMode,
    ): String =
        "$path/$memberId/$lessonId/$mode"
}
