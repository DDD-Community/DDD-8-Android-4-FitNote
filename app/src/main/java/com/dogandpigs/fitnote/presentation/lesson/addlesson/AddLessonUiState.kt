package com.dogandpigs.fitnote.presentation.lesson.addlesson

import com.dogandpigs.fitnote.presentation.util.format
import java.util.Date

internal data class AddLessonUiState(
    val title: String = "",
    val dateMilliSeconds: Long = System.currentTimeMillis(),
    val btnRoutineCloseVisibility: Boolean = false,
    val routineList: List<Routine> = emptyList(),
    val isExpand: Boolean = false
) {
    val dateString: String
        get() = Date(dateMilliSeconds).format()
}
