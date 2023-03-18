package com.dogandpigs.fitnote.presentation.lesson.addlesson

import androidx.lifecycle.viewModelScope
import com.dogandpigs.fitnote.data.repository.LessonRepository
import com.dogandpigs.fitnote.presentation.base.BaseViewModel
import com.dogandpigs.fitnote.presentation.base.Event
import com.dogandpigs.fitnote.presentation.lesson.Exercise
import com.dogandpigs.fitnote.presentation.lesson.toPresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class AddLessonViewModel @Inject constructor(
    private val lessonRepository: LessonRepository
) : BaseViewModel<AddLessonUiState>() {
    override fun createInitialState(): AddLessonUiState = AddLessonUiState()

    private val _eventStateFlow: MutableStateFlow<Event> = MutableStateFlow(Event.None)
    val eventStateFlow = _eventStateFlow.asStateFlow()

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
                }.onFailure {
                    showToast("불러오기", it.message)
                }
            }
        }

        setState {
            copy(
                id = memberId
            )
        }
    }

    fun addLesson() = currentState {
        viewModelScope.launch {
            runCatching {
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
            }.onFailure {
                showToast("저장", it.message)
            }
        }
    }

    fun setDateMilliSeconds(dateMilliSeconds: Long?) = currentState {
        runCatching {
            checkNotNull(dateMilliSeconds)
        }.onSuccess {
            setState {
                copy(
                    dateMilliSeconds = it
                )
            }
        }
    }

    fun addExercise() = currentState {
        val exerciseList = mutableListOf<Exercise>().apply {
            addAll(exercises)
            add(Exercise())
        }

        setExercisesUiState(exerciseList.toList())
    }

    fun removeExercise(index: Int) = currentState {
        val exerciseList = mutableListOf<Exercise>().apply {
            addAll(exercises)
            removeAt(index)
        }

        setExercisesUiState(exerciseList.toList())
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

        setExercisesUiState(exerciseList.toList())
    }

    fun addExerciseSet(
        exerciseIndex: Int,
    ) = currentState {
        val exerciseList = mutableListOf<Exercise>()
        exerciseList.addAll(exercises)

        val exerciseSetList = mutableListOf<Exercise.ExerciseSet>()
        exerciseSetList.addAll(exerciseList[exerciseIndex].sets)

        exerciseSetList.add(
            Exercise.ExerciseSet(
                setIndex = exerciseSetList.size + 1,
            )
        )

        exerciseList[exerciseIndex] = exerciseList[exerciseIndex].copy(
            sets = exerciseSetList.toList()
        )

        setExercisesUiState(exerciseList.toList())
        setExerciseState(exerciseIndex)
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

        setExercisesUiState(exerciseList.toList())
        setExerciseState(exerciseIndex)
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

        setExercisesUiState(exerciseList.toList())
        setExerciseState(exerciseIndex)
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

        setExercisesUiState(exerciseList.toList())
        setExerciseState(exerciseIndex)
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
            setExercisesUiState(it)
        }.onFailure {
            // TODO
        }
    }

    fun changeAllWeight(
        exerciseIndex: Int,
        weight: String,
    ) = currentState {
        runCatching {
            val newWeight = weight.toDoubleOrNull()
            checkNotNull(newWeight) { "숫자만 입력해주세요." }

            mutableListOf<Exercise>().apply {
                addAll(exercises)
                this[exerciseIndex] = exercises[exerciseIndex].copy(
                    sets = exercises[exerciseIndex].sets.map {
                        it.copy(
                            weight = newWeight
                        )
                    }
                )
            }.toList()
        }.onSuccess {
            setExercisesUiState(it)
        }.onFailure {
            showToast(
                title = "전체 몸무게 변경",
                message = it.message,
            )
        }
    }

    fun changeAllCount(
        exerciseIndex: Int,
        count: String,
    ) = currentState {
        runCatching {
            val newCount = count.toIntOrNull()
            checkNotNull(newCount) { "숫자만 입력해주세요." }

            mutableListOf<Exercise>().apply {
                addAll(exercises)
                this[exerciseIndex] = exercises[exerciseIndex].copy(
                    sets = exercises[exerciseIndex].sets.map {
                        it.copy(
                            count = newCount
                        )
                    }
                )
            }.toList()
        }.onSuccess {
            setExercisesUiState(it)
        }.onFailure {
            showToast(
                title = "전체 횟수 변경",
                message = it.message,
            )
        }
    }

    private fun changeExerciseList(
        exerciseIndex: Int,
        newExercise: Exercise,
    ): List<Exercise> =
        currentState {
            val exerciseList = mutableListOf<Exercise>().apply {
                addAll(exercises)
            }
            exerciseList[exerciseIndex] = newExercise
            exerciseList.toList()
        }

    private fun setExerciseState(
        exerciseIndex: Int,
    ) {
        setNumberOfSets(exerciseIndex)
        setMainWeight(exerciseIndex)
        setMainCount(exerciseIndex)
    }

    private fun setNumberOfSets(
        exerciseIndex: Int,
    ) = currentState {
        runCatching {
            changeExerciseList(
                exerciseIndex = exerciseIndex,
                newExercise = exercises[exerciseIndex].copy(
                    numberOfSets = exercises[exerciseIndex].sets.size
                ),
            )
        }.onSuccess {
            setExercisesUiState(it)
        }
    }

    private fun setMainWeight(
        exerciseIndex: Int,
    ) = currentState {
        runCatching {
            changeExerciseList(
                exerciseIndex = exerciseIndex,
                newExercise = exercises[exerciseIndex].copy(
                    mainWeight = exercises[exerciseIndex].maxWeightExerciseSet.weight
                ),
            )
        }.onSuccess {
            setExercisesUiState(it)
        }
    }

    private fun setMainCount(
        exerciseIndex: Int,
    ) = currentState {
        runCatching {
            changeExerciseList(
                exerciseIndex = exerciseIndex,
                newExercise = exercises[exerciseIndex].copy(
                    mainCount = exercises[exerciseIndex].maxWeightExerciseSet.count
                ),
            )
        }.onSuccess {
            setExercisesUiState(it)
        }
    }

    private fun setExercisesUiState(exercises: List<Exercise>) {
        setState {
            copy(
                exercises = exercises,
            )
        }
    }

    private fun showToast(
        title: String,
        message: String?,
    ) {
        _eventStateFlow.value = Event.Toast("$title 실패\n사유 : $message")
    }
}
