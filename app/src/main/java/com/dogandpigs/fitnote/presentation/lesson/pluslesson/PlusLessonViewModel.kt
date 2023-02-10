package com.dogandpigs.fitnote.presentation.lesson.pluslesson

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.dogandpigs.fitnote.presentation.base.BaseViewModel
import com.dogandpigs.fitnote.presentation.lesson.Exercise
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class PlusLessonViewModel @Inject constructor(

) : BaseViewModel<PlusLessonUiState>() {
    override fun createInitialState(): PlusLessonUiState = PlusLessonUiState()

    fun initialize(memberId: Int) = currentState {
        setState {
            copy(
                id = memberId
            )
        }

        // TODO Develop 후 삭제
        viewModelScope.launch {
            state.collect {
                Log.d("aa12", "it : $it")
            }
        }
    }

    fun setDateMilliSeconds(dateMilliSeconds: Long) = currentState {
        setState {
            copy(
                dateMilliSeconds = dateMilliSeconds
            )
        }
    }

    fun addExercise() = currentState {
        val exerciseList = mutableListOf<Exercise>()
        exerciseList.addAll(state.value.exercises)

        exerciseList.add(Exercise())

        setState {
            copy(
                exercises = exerciseList.toList(),
            )
        }
    }

    fun changeExerciseName(
        index: Int,
        name: String,
    ) = currentState {
        val exerciseList = mutableListOf<Exercise>()
        exerciseList.addAll(state.value.exercises)

        exerciseList[index] = exerciseList[index].copy(
            name = name
        )

        setState {
            copy(
                exercises = exerciseList.toList(),
            )
        }
    }

    fun addExerciseSet(
        index: Int,
    ) = currentState {
        val exerciseList = mutableListOf<Exercise>()
        exerciseList.addAll(state.value.exercises)

        val exerciseSetList = mutableListOf<Exercise.ExerciseSet>()
        exerciseSetList.addAll(exerciseList[index].sets)

        exerciseSetList.add(
            Exercise.ExerciseSet(
                setIndex = exerciseSetList.size + 1,
            )
        )

        exerciseList[index] = exerciseList[index].copy(
            sets = exerciseSetList.toList()
        )

        setState {
            copy(
                exercises = exerciseList.toList(),
            )
        }
    }

    fun removeExerciseSet(
        exerciseIndex: Int,
        exerciseSetIndex: Int,
    ) = currentState {
        val exerciseList = mutableListOf<Exercise>()
        exerciseList.addAll(state.value.exercises)

        val exerciseSetList = mutableListOf<Exercise.ExerciseSet>()
        exerciseSetList.addAll(exerciseList[exerciseIndex].sets)

        exerciseSetList.removeAt(exerciseSetIndex)

        exerciseList[exerciseIndex] = exerciseList[exerciseIndex].copy(
            sets = exerciseSetList.toList()
        )

        setState {
            copy(
                exercises = exerciseList.toList(),
            )
        }
    }
//
//    fun toggleExerciseSetIsDone(
//        exerciseIndex: Int,
//        exerciseSetIndex: Int,
//    ) {
//        val exerciseList = mutableListOf<MemberLessonUiState.Exercise>()
//        exerciseList.addAll(uiState.value.exercises)
//
//        val exerciseSetList = mutableListOf<MemberLessonUiState.Exercise.ExerciseSet>()
//        exerciseSetList.addAll(exerciseList[exerciseIndex].sets)
//
//        exerciseSetList[exerciseSetIndex] = exerciseSetList[exerciseSetIndex].toggle()
//        exerciseList[exerciseIndex] = exerciseList[exerciseIndex].copy(
//            sets = exerciseSetList.toList()
//        )
//
//        val allTrue = exerciseList[exerciseIndex].sets.map { it.isDone }.contains(false).not()
//        exerciseList[exerciseIndex] = exerciseList[exerciseIndex].copy(
//            isDone = allTrue
//        )
//
//        uiState.value = uiState.value.copy(
//            exercises = exerciseList.toList(),
//        )
//    }
//
//    fun changeWeight(
//        value: String,
//        exerciseIndex: Int,
//        exerciseSetIndex: Int,
//    ) {
//        val exerciseList = mutableListOf<MemberLessonUiState.Exercise>()
//        exerciseList.addAll(uiState.value.exercises)
//
//        val exerciseSetList = mutableListOf<MemberLessonUiState.Exercise.ExerciseSet>()
//        exerciseSetList.addAll(exerciseList[exerciseIndex].sets)
//
//        exerciseSetList[exerciseSetIndex] = exerciseSetList[exerciseSetIndex].copy(
//            weight = if (value.isEmpty()) {
//                0.0
//            } else {
//                value.toDouble()
//            }
//        )
//
//        exerciseList[exerciseIndex] = exerciseList[exerciseIndex].copy(
//            sets = exerciseSetList.toList()
//        )
//        uiState.value = uiState.value.copy(
//            exercises = exerciseList.toList(),
//        )
//    }
//
//    fun changeCount(
//        value: String,
//        exerciseIndex: Int,
//        exerciseSetIndex: Int,
//    ) {
//        val exerciseList = mutableListOf<MemberLessonUiState.Exercise>()
//        exerciseList.addAll(uiState.value.exercises)
//
//        val exerciseSetList = mutableListOf<MemberLessonUiState.Exercise.ExerciseSet>()
//        exerciseSetList.addAll(exerciseList[exerciseIndex].sets)
//
//        exerciseSetList[exerciseSetIndex] = exerciseSetList[exerciseSetIndex].copy(
//            count = if (value.isEmpty()) {
//                0
//            } else {
//                value.toInt()
//            }
//        )
//
//        exerciseList[exerciseIndex] = exerciseList[exerciseIndex].copy(
//            sets = exerciseSetList.toList()
//        )
//        uiState.value = uiState.value.copy(
//            exercises = exerciseList.toList(),
//        )
//    }
}
