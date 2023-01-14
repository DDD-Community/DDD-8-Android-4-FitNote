package com.dogandpigs.fitnote.presentation.lesson.memberlesson

internal data class MemberLessonUiState(
    val userName: String = "",
    val exercises: List<Exercise> = emptyList(),
) {
    data class Exercise(
        val name: String,
        val sets: List<ExerciseSet>,
        val isDone: Boolean,
    ) {
        val numberOfSets: Int
            get() = sets.size

        val mainWeight: Double
            get() = sets.firstOrNull()?.weight ?: 0.0

        val mainCount: Int
            get() = sets.firstOrNull()?.count ?: 0

        fun toggle(): Exercise = copy(
            sets = if (isDone) {
                sets.map {
                    it.uncheck()
                }
            } else {
                sets.map {
                    it.check()
                }
            },
            isDone = !isDone,
        )

        data class ExerciseSet(
            val setIndex: Int,
            val weight: Double,
            val count: Int,
            val isDone: Boolean,
        ) {
            fun toggle(): ExerciseSet = copy(
                isDone = !isDone
            )

            fun check(): ExerciseSet = copy(
                isDone = true
            )

            fun uncheck(): ExerciseSet = copy(
                isDone = false
            )
        }
    }
}
