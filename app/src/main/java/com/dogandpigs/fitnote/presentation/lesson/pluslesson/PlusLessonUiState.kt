package com.dogandpigs.fitnote.presentation.lesson.pluslesson

import com.dogandpigs.fitnote.presentation.lesson.Exercise
import com.dogandpigs.fitnote.presentation.util.format
import com.dogandpigs.fitnote.presentation.util.formatYYYYMMDD
import java.util.Date

internal data class PlusLessonUiState(
    val id: Int = 0,
    val dateMilliSeconds: Long = System.currentTimeMillis(),
    val exercises: List<Exercise> = listOf(
        Exercise(
            name = "운동명",
            sets = listOf(
                Exercise.ExerciseSet(
                    setIndex = 0,
                    weight = 0.0,
                    count = 0,
                    isDone = false,
                )
            ),
            isFold = false,
        )
    ),
) {
    val dateString: String
        get() = Date(dateMilliSeconds).format()
    val dateStringYYYYMMDD: String
        get() = Date(dateMilliSeconds).formatYYYYMMDD()
}
