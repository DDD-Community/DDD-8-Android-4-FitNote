package com.dogandpigs.fitnote.presentation.lesson

internal data class Exercise(
    val name: String = "",
    val numberOfSets: Int = 1,
    val mainWeight: Double = 0.0,
    val mainCount: Int = 0,
    val sets: List<ExerciseSet> = listOf(ExerciseSet()),
    val isFold: Boolean = true,
) {
    val maxWeightExerciseSet: ExerciseSet
        get() = sets.maxBy { sets -> sets.weight }
            .let { exerciseSet ->
                sets.first {
                    it == exerciseSet
                }
            }

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
        val lessonId: Int = -1,
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
