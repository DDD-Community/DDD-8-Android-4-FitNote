package com.dogandpigs.fitnote.presentation.lesson

enum class LessonMode(
    val value: Int,
) {
    UNKNOWN(-1),
    SHOW(0),
    ADD(1),
    EDIT(2),
}
