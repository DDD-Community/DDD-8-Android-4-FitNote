package com.dogandpigs.fitnote.presentation.lesson.memberlesson

import com.dogandpigs.fitnote.presentation.lesson.Exercise

internal data class MemberLessonUiState(
    val userName: String = "",
    val exercises: List<Exercise> = emptyList(),
)
