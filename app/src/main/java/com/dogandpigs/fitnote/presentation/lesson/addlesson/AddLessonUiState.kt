package com.dogandpigs.fitnote.presentation.lesson.addlesson

internal data class AddLessonUiState(
    val title: String = "",
    val dateString: String = "",
    val dateMilliSeconds: Long = 0L,
    val btnRoutineCloseVisibility: Boolean = false,
    val routineList: List<Routine> = emptyList()
)
