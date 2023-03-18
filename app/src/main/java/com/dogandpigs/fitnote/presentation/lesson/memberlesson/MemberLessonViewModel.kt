package com.dogandpigs.fitnote.presentation.lesson.memberlesson

import androidx.lifecycle.viewModelScope
import com.dogandpigs.fitnote.data.repository.LessonRepository
import com.dogandpigs.fitnote.presentation.base.BaseViewModel
import com.dogandpigs.fitnote.presentation.lesson.Exercise
import com.dogandpigs.fitnote.presentation.lesson.toPresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class MemberLessonViewModel @Inject constructor(
    private val lessonRepository: LessonRepository,
) : BaseViewModel<MemberLessonUiState>() {
    override fun createInitialState(): MemberLessonUiState = MemberLessonUiState()

    private val tempList = mutableListOf<Temp>()

    data class Temp(
        val memberId: Int,
        val lessonId: Int,
        val today: Int,
    )

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
                        exercises = it.toPresentation(
                            id = memberId,
                            today = lessonDate,
                            tempListAction = { id, today, lessonId ->
                                tempList.add(
                                    Temp(
                                        memberId = id,
                                        today = today,
                                        lessonId = lessonId,
                                    )
                                )
                            },
                            isOnlyReady = true,
                        ),
                    )
                }
            }
        }
    }

    fun complete() = currentState {
        runCatching {
            tempList.toList().forEach { temp ->
                viewModelScope.launch {
                    lessonRepository.putLessonComplete(
                        id = temp.memberId,
                        lessonId = temp.lessonId,
                        today = temp.today,
                    )
                }
            }
        }.onSuccess {
            setState {
                copy(
                    isNext = true,
                )
            }
        }
    }

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
