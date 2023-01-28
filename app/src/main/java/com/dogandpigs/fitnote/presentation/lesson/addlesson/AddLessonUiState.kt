package com.dogandpigs.fitnote.presentation.lesson.addlesson

import com.dogandpigs.fitnote.core.FormatUtil

internal data class AddLessonUiState(
    val title: String = "",
    val dateMilliSeconds: Long = 0L,
    val btnRoutineCloseVisibility: Boolean = false,
    val routineList: List<Routine> = emptyList(),
    val isExpand: Boolean = false
) {
    val dateString: String
        get() = FormatUtil.millToDate(dateMilliSeconds)
}
