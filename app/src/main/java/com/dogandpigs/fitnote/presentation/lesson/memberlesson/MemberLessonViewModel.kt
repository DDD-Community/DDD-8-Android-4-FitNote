package com.dogandpigs.fitnote.presentation.lesson.memberlesson

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.dogandpigs.fitnote.data.repository.LessonRepository
import com.dogandpigs.fitnote.data.source.remote.model.LessonDetailResponse
import com.dogandpigs.fitnote.presentation.base.BaseViewModel
import com.dogandpigs.fitnote.presentation.lesson.Exercise
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class MemberLessonViewModel @Inject constructor(
    private val lessonRepository: LessonRepository,
) : BaseViewModel<MemberLessonUiState>() {
    override fun createInitialState(): MemberLessonUiState = MemberLessonUiState()

    fun initialize(
        memberId: Int,
        lessonDate: Int,
    ) {
        getLessonDetail(
            memberId = memberId,
            lessonDate = lessonDate,
        )
    }

    private fun getLessonDetail(
        memberId: Int,
        lessonDate: Int,
    ) = currentState {
        Log.d("aa12", "getLessonDetail")
        viewModelScope.launch {
            kotlin.runCatching {
                lessonRepository.getLessonDetail(
                    id = memberId,
                    today = lessonDate,
                )
            }.onSuccess {
                setState {
                    copy(
                        userName = it?.memberInfo?.userName ?: "",
                        exercises = it.toPresentation(),
                    )
                }
//                Log.d("aa12", "it : $it")
//                it.toPresentation()
//                Log.d("aa12", "it : $it")
            }
        }
    }

    private fun LessonDetailResponse?.toPresentation(): List<Exercise> =
        this?.run {
            this.lessonInfo.flatten().groupBy {
                it.name
            }.let { maps ->
                val exerciseList = mutableListOf<Exercise>()
                maps.forEach { (name, lessonDescriptionList) ->
                    exerciseList.add(
                        Exercise(
                            name = name,
                            sets = lessonDescriptionList.let {
                                val list = mutableListOf<Exercise.ExerciseSet>()
                                it.forEach { lessonDescription ->
                                    list.add(
                                        Exercise.ExerciseSet(
                                            setIndex = lessonDescription.set,
                                            weight = lessonDescription.weight.toDouble(),
                                            count = lessonDescription.count,
                                            // TODO
                                            isDone = false,
                                        )
                                    )
                                }
                                list
                            },
                            isFold = false,
                        )
                    )
                }
                exerciseList
            }
        } ?: emptyList()

    fun toggleExerciseIsDone(index: Int) = currentState {
        val exerciseList = mutableListOf<Exercise>()
        exerciseList.addAll(exercises)
        exerciseList[index] = exerciseList[index].toggle()

        setState {
            copy(
                exercises = exerciseList.toList(),
            )
        }
    }

    fun toggleExerciseSetIsDone(
        exerciseIndex: Int,
        exerciseSetIndex: Int,
    ) = currentState {
        val exerciseList = mutableListOf<Exercise>()
        exerciseList.addAll(exercises)

        val exerciseSetList = mutableListOf<Exercise.ExerciseSet>()
        exerciseSetList.addAll(exerciseList[exerciseIndex].sets)

        exerciseSetList[exerciseSetIndex] = exerciseSetList[exerciseSetIndex].toggle()
        exerciseList[exerciseIndex] = exerciseList[exerciseIndex].copy(
            sets = exerciseSetList.toList()
        )

        val allTrue = exerciseList[exerciseIndex].sets.map { it.isDone }.contains(false).not()
        exerciseList[exerciseIndex] = exerciseList[exerciseIndex].copy(
            isFold = allTrue
        )

        setState {
            copy(
                exercises = exerciseList.toList(),
            )
        }
    }

    fun changeWeight(
        value: String,
        exerciseIndex: Int,
        exerciseSetIndex: Int,
    ) = currentState {
        val exerciseList = mutableListOf<Exercise>()
        exerciseList.addAll(exercises)

        val exerciseSetList = mutableListOf<Exercise.ExerciseSet>()
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
        setState {
            copy(
                exercises = exerciseList.toList(),
            )
        }
    }

    fun changeCount(
        value: String,
        exerciseIndex: Int,
        exerciseSetIndex: Int,
    ) = currentState {
        val exerciseList = mutableListOf<Exercise>()
        exerciseList.addAll(exercises)

        val exerciseSetList = mutableListOf<Exercise.ExerciseSet>()
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
        setState {
            copy(
                exercises = exerciseList.toList(),
            )
        }
    }
}
