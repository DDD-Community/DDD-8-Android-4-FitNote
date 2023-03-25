package com.dogandpigs.fitnote.presentation.lesson.addlesson

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

internal sealed interface AddLessonEvent {
    object None : AddLessonEvent

    data class Toast(
        val message: String
    ) : AddLessonEvent

    data class CustomToast(
        val message: String,
        val bottomPadding: Dp = 0.dp,
    ) : AddLessonEvent
}
