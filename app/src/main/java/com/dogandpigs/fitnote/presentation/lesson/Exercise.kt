package com.dogandpigs.fitnote.presentation.lesson

internal data class Exercise(
    val name: String = "",
    val sets: List<ExerciseSet> = listOf(ExerciseSet()),
    val isFold: Boolean = true,
) {
    private val maxWeightExerciseSet: ExerciseSet
        get() = sets.maxBy { sets -> sets.weight }
            .let { exerciseSet ->
                sets.first {
                    it == exerciseSet
                }
            }

    val numberOfSets: Int
        get() = sets.size

    val mainWeight: Double
        get() = maxWeightExerciseSet.weight

    val mainCount: Int
        get() = maxWeightExerciseSet.count

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
