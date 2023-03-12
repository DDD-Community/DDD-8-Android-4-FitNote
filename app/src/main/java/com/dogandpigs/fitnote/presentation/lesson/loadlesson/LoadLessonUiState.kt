package com.dogandpigs.fitnote.presentation.lesson.loadlesson

import com.dogandpigs.fitnote.presentation.ui.DefaultValue

internal data class LoadLessonUiState(
    val selectedMemberId: Int = DefaultValue.ITEM_INDEX_NOT_SELECTED,
    val selectedRoutineId: Int = DefaultValue.ITEM_INDEX_NOT_SELECTED,
    val memberList: List<Member> = emptyList(),
    val routineList: List<Routine> = emptyList(),
    val isLoad: Boolean = false,
) {
    data class Member(
        val id: Int,
        val name: String,
    )

    data class Routine(
        val id: Int,
        val title: String,
        val exerciseNameList: List<String>,
        val exerciseList: List<Exercise>,
        val exerciseListVisible: Boolean,
    ) {
        data class Exercise(
            val name: String,
            val set: Int,
            val weight: String,
            val count: Int,
        )
    }
}
