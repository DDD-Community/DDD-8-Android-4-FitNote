package com.dogandpigs.fitnote.presentation.lesson

internal data class Exercise(
    val name: String = "",
    val sets: List<ExerciseSet> = listOf(ExerciseSet()),
    val isFold: Boolean = true,
) {
    val numberOfSets: Int
        get() = sets.size

    val mainWeight: Double
        get() = sets.firstOrNull()?.weight ?: 0.0

    val mainCount: Int
        get() = sets.firstOrNull()?.count ?: 0

    fun toggle(): Exercise = copy(
        sets = if (isFold) {
            sets.map {
                it.uncheck()
            }
        } else {
            sets.map {
                it.check()
            }
        },
        isFold = !isFold,
    )

    data class ExerciseSet(
        val setIndex: Int = 1,
        val weight: Double = 0.0,
        val count: Int = 0,
        val isDone: Boolean = false,
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
