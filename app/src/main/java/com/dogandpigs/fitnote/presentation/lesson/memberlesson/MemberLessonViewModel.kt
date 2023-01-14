package com.dogandpigs.fitnote.presentation.lesson.memberlesson

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
internal class MemberLessonViewModel @Inject constructor(

) : ViewModel() {
    val uiState: MutableStateFlow<MemberLessonUiState> =
        MutableStateFlow(previewUiState)

    fun toggleExerciseIsDone(index: Int) {
        val exerciseList = mutableListOf<MemberLessonUiState.Exercise>()
        exerciseList.addAll(uiState.value.exercises)
        exerciseList[index] = exerciseList[index].toggle()

        uiState.value = uiState.value.copy(
            exercises = exerciseList.toList(),
        )
    }

    fun toggleExerciseSetIsDone(
        exerciseIndex: Int,
        exerciseSetIndex: Int,
    ) {
        val exerciseList = mutableListOf<MemberLessonUiState.Exercise>()
        exerciseList.addAll(uiState.value.exercises)

        val exerciseSetList = mutableListOf<MemberLessonUiState.Exercise.ExerciseSet>()
        exerciseSetList.addAll(exerciseList[exerciseIndex].sets)

        exerciseSetList[exerciseSetIndex] = exerciseSetList[exerciseSetIndex].toggle()
        exerciseList[exerciseIndex] = exerciseList[exerciseIndex].copy(
            sets = exerciseSetList.toList()
        )

        val allTrue = exerciseList[exerciseIndex].sets.map { it.isDone }.contains(false).not()
        exerciseList[exerciseIndex] = exerciseList[exerciseIndex].copy(
            isDone = allTrue
        )

        uiState.value = uiState.value.copy(
            exercises = exerciseList.toList(),
        )
    }

    fun changeWeight(
        value: String,
        exerciseIndex: Int,
        exerciseSetIndex: Int,
    ) {
        val exerciseList = mutableListOf<MemberLessonUiState.Exercise>()
        exerciseList.addAll(uiState.value.exercises)

        val exerciseSetList = mutableListOf<MemberLessonUiState.Exercise.ExerciseSet>()
        exerciseSetList.addAll(exerciseList[exerciseIndex].sets)

        exerciseSetList[exerciseSetIndex] = exerciseSetList[exerciseSetIndex].copy(
            weight = if (value.isEmpty()) {
                0.0
            } else {
                value.toDouble()
            }
        )

        exerciseList[exerciseIndex] = exerciseList[exerciseIndex].copy(
            sets = exerciseSetList.toList()
        )
        uiState.value = uiState.value.copy(
            exercises = exerciseList.toList(),
        )
    }

    fun changeCount(
        value: String,
        exerciseIndex: Int,
        exerciseSetIndex: Int,
    ) {
        val exerciseList = mutableListOf<MemberLessonUiState.Exercise>()
        exerciseList.addAll(uiState.value.exercises)

        val exerciseSetList = mutableListOf<MemberLessonUiState.Exercise.ExerciseSet>()
        exerciseSetList.addAll(exerciseList[exerciseIndex].sets)

        exerciseSetList[exerciseSetIndex] = exerciseSetList[exerciseSetIndex].copy(
            count = if (value.isEmpty()) {
                0
            } else {
                value.toInt()
            }
        )

        exerciseList[exerciseIndex] = exerciseList[exerciseIndex].copy(
            sets = exerciseSetList.toList()
        )
        uiState.value = uiState.value.copy(
            exercises = exerciseList.toList(),
        )
    }
}
