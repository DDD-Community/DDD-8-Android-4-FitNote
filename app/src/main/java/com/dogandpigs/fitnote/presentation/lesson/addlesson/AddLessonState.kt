package com.dogandpigs.fitnote.presentation.lesson.addlesson

import com.dogandpigs.fitnote.presentation.util.format
import com.dogandpigs.fitnote.presentation.util.formatYYYYMMDD
import java.util.Date

internal data class AddLessonState(
    val id: Int = 0,
    val dateMilliSeconds: Long = System.currentTimeMillis(),
    val btnRoutineCloseVisibility: Boolean = false,
    val routineList: List<Routine> = emptyList(),
    val isExpand: Boolean = false,
    val exerciseList: List<Routine> = emptyList(),
    val currentRoutine: Routine = Routine()
) {
    val dateString: String
        get() = Date(dateMilliSeconds).format()
    val dateStringYYYYMMDD: String
        get() = Date(dateMilliSeconds).formatYYYYMMDD()
}
