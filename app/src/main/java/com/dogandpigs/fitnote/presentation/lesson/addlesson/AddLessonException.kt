package com.dogandpigs.fitnote.presentation.lesson.addlesson

internal data class AddLessonException(
    override val message: String,
) : Throwable(
    message = message,
)

