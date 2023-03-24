package com.dogandpigs.fitnote.presentation.lesson.addlesson

import com.dogandpigs.fitnote.presentation.lesson.Exercise
import com.dogandpigs.fitnote.presentation.lesson.LessonMode
import com.dogandpigs.fitnote.presentation.util.format
import com.dogandpigs.fitnote.presentation.util.formatYYYYMMDD
import java.util.Date

internal data class AddLessonUiState(
    val id: Int = 0,
    val mode: LessonMode = LessonMode.ADD,
    val dateMilliSeconds: Long = System.currentTimeMillis(),
    val exercises: List<Exercise> = listOf(
        Exercise()
    ),
    val isSuccess: Boolean = false,
) {
    val dateString: String
        get() = Date(dateMilliSeconds).format()
    val dateStringYYYYMMDD: String
        get() = Date(dateMilliSeconds).formatYYYYMMDD()
}
