package com.dogandpigs.fitnote.presentation.lesson.pluslesson

import androidx.lifecycle.viewModelScope
import com.dogandpigs.fitnote.data.repository.LessonRepository
import com.dogandpigs.fitnote.presentation.base.BaseViewModel
import com.dogandpigs.fitnote.presentation.lesson.Exercise
import com.dogandpigs.fitnote.presentation.lesson.addlesson.Routine
import com.dogandpigs.fitnote.presentation.lesson.toPresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class PlusLessonViewModel @Inject constructor(
    private val lessonRepository: LessonRepository
) : BaseViewModel<PlusLessonUiState>() {
    override fun createInitialState(): PlusLessonUiState = PlusLessonUiState()

    fun initialize(
        memberId: Int,
        lessonId: Int,
    ) {
        viewModelScope.launch {
            if (lessonId > 0) {
                runCatching {
                    lessonRepository.getLessonDetail(
                        id = memberId,
                        today = lessonId,
                    )
                }.onSuccess {
                    setState {
                        copy(
                            exercises = it.toPresentation(
                                id = memberId,
                                today = lessonId,
                            ),
                        )
                    }
                }
            }
        }

        setState {
            copy(
                id = memberId
            )
        }
    }

    fun plusLesson() = currentState {
        viewModelScope.launch {
            kotlin.runCatching {
                exercises.forEach { exerciseList ->
                    exerciseList.sets.forEachIndexed { index, exerciseSet ->
                        val routine = Routine(
                            id = id,
                            index = index,
                            name = exerciseList.name,
                            set = exerciseSet.setIndex,
                            weight = exerciseSet.weight.toInt(),
                            count = exerciseSet.count,
                            today = dateStringYYYYMMDD,
                        )
                        lessonRepository.addLesson(routine)
                    }
                }
            }.onSuccess {
                setState {
                    copy(
                        isSuccess = true,
                    )
                }
            }
        }
    }

    fun setDateMilliSeconds(dateMilliSeconds: Long?) = setState {
        checkNotNull(dateMilliSeconds)
        copy(
            dateMilliSeconds = dateMilliSeconds
        )
    }

    fun addExercise() = currentState {
        val exerciseList = mutableListOf<Exercise>().apply {
            addAll(exercises)
            add(Exercise())
        }

        setState {
            copy(
                exercises = exerciseList.toList(),
            )
        }
    }

    fun removeExercise(index: Int) = currentState {
        val exerciseList = mutableListOf<Exercise>().apply {
            addAll(exercises)
            removeAt(index)
        }

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
        exerciseList.addAll(exercises)

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
        exerciseList.addAll(exercises)

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
        exerciseList.addAll(exercises)

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

    fun changeAllSet(
        exerciseIndex: Int,
        set: String,
    ) = currentState {
        runCatching {
            val changedSet = set.toIntOrNull()?.let {
                if (it > 10) {
                    10
                    // TODO 토스트 10보다 클 때 최대 10개 입니다. 로딩 필요?
                } else {
                    it
                }
            } ?: 0

            val exerciseList = mutableListOf<Exercise>()
            exerciseList.addAll(exercises)

            val exerciseSetList = mutableListOf<Exercise.ExerciseSet>()
            exerciseSetList.addAll(exerciseList[exerciseIndex].sets)

            if (exerciseSetList.size < changedSet) {
                repeat(changedSet - exerciseSetList.size) {
                    exerciseSetList.add(
                        Exercise.ExerciseSet(
                            setIndex = exerciseSetList.size + 1,
                        )
                    )
                }
            } else if (exerciseSetList.size > changedSet) {
                repeat(exerciseSetList.size - changedSet) {
                    exerciseSetList.removeAt(exerciseSetList.size - 1)
                }
            }

            exerciseList[exerciseIndex] = exerciseList[exerciseIndex].copy(
                sets = exerciseSetList.toList()
            )

            exerciseList.toList()
        }.onSuccess {
            setState {
                copy(
                    exercises = it,
                )
            }
        }.onFailure {
            // TODO
        }
    }
}
