package com.dogandpigs.fitnote.presentation.lesson.addlesson

internal sealed interface AddLessonEvent {
    object None : AddLessonEvent

    data class Toast(
        val message: String
    ) : AddLessonEvent
}
