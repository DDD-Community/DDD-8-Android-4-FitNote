package com.dogandpigs.fitnote.presentation.lesson.addlesson

import androidx.lifecycle.viewModelScope
import com.dogandpigs.fitnote.data.repository.LessonRepository
import com.dogandpigs.fitnote.presentation.base.BaseViewModel
import com.dogandpigs.fitnote.presentation.base.Event
import com.dogandpigs.fitnote.presentation.lesson.Exercise
import com.dogandpigs.fitnote.presentation.lesson.LessonMode
import com.dogandpigs.fitnote.presentation.lesson.toLesson
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

    private var originExerciseList: List<Exercise>? = null

    fun initialize(
        memberId: Int,
        lessonId: Int,
        mode: LessonMode,
    ) {
        viewModelScope.launch {
            if (lessonId > 0) {
                runCatching {
                    lessonRepository.getLessonDetail(
                        id = memberId,
                        today = lessonId,
                    )
                }.onSuccess {
                    val exercises = it.toPresentation(
                        id = memberId,
                        today = lessonId,
                    )
                    setState {
                        copy(
                            exercises = exercises,
                        )
                    }
                    if (mode == LessonMode.EDIT) {
                        originExerciseList = exercises
                    }
                }.onFailure {
                    showToast("불러오기", it.message)
                }
            }
        }

        setState {
            copy(
                id = memberId,
                mode = mode,
            )
        }
    }

    fun saveLesson() = currentState {
        viewModelScope.launch {
            runCatching {
                when (mode) {
                    LessonMode.ADD -> {
                        addLesson()
                    }
                    LessonMode.EDIT -> {
//                        editLesson()
                    }
                    else -> {

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

    private suspend fun addLesson() = currentState {
        exercises.forEach { exerciseList ->
            exerciseList.sets.forEachIndexed { index, exerciseSet ->
                val lesson = exerciseSet.toLesson(
                    id = id,
                    name = exerciseList.name,
                    today = dateStringYYYYMMDD,
                )
                lessonRepository.addLesson(lesson)
            }
        }
    }

    // TODO
//    private suspend fun editLesson() = currentState {
//        // 삭제
//        val originLessonIdList = originExerciseList?.flatMap {
//            it.sets.map { exerciseSet ->
//                exerciseSet.lessonId
//            }
//        }
//        checkNotNull(originLessonIdList)
//
//        val newLessonIdList = exercises.map {
//            it.sets.map { exerciseSet ->
//                exerciseSet.lessonId
//            }
//        }.flatten()
//
//        val deleteLessonIdList = newLessonIdList.filterNot {
//            originLessonIdList.contains(it)
//        }
//        val deleteLessonList = originExerciseList?.flatMap {
//            it.sets.filter { exerciseSet ->
//                deleteLessonIdList.contains(exerciseSet.lessonId)
//            }
//        }
//
//        // 수정
//        // 추가
//
//        // 추가할 것
//        originExerciseList?.zipWithNext { a, b -> }
//
//        originExerciseList?.forEach { exerciseList ->
//            exerciseList.sets.filter {
//                it.id in
//            }
//        }
//        exercises.forEach { exerciseList ->
//            exerciseList.sets.forEachIndexed { index, exerciseSet ->
//                exerciseSet.lessonId
//                // 기존에 있으면 업데이트
//                // 없어진 거 삭제
//                // id를 비교해서 없으면 삽입, 있으면 비교해서 업데이트 id가 없어졌으면 삭제
//
//                lessonRepository.addLesson(routine)
//            }
//        }
//    }

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
