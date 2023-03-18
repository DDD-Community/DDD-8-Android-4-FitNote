package com.dogandpigs.fitnote.presentation.lesson.loadlesson

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.dogandpigs.fitnote.data.repository.LessonRepository
import com.dogandpigs.fitnote.data.repository.MemberRepository
import com.dogandpigs.fitnote.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class LoadLessonViewModel @Inject constructor(
    private val memberRepository: MemberRepository,
    private val lessonRepository: LessonRepository
) : BaseViewModel<LoadLessonUiState>() {
    override fun createInitialState(): LoadLessonUiState = LoadLessonUiState()

    fun initialize() {
        getMemberList()
    }

    fun setSelectedMemberId(memberId: Int) {
        setState {
            copy(
                selectedMemberId = memberId,
            )
        }
    }

    fun setSelectedRoutineId(routineId: Int) {
        setState {
            copy(
                selectedRoutineId = routineId,
            )
        }
    }

    // TODO 로딩 추가
    fun toggleFold(routineIndex: Int) {
        viewModelScope.launch {
            currentState {
                runCatching {
                    if (routineList[routineIndex].exerciseList.isEmpty()) {
                        getExerciseList(routineIndex)
                    } else {
                        null
                    }
                }.onSuccess {
                    val newRoutineList = it ?: routineList.toMutableList().apply {
                        this[routineIndex] = routineList[routineIndex].copy(
                            exerciseListVisible = !routineList[routineIndex].exerciseListVisible
                        )
                    }.toList()

                    setState {
                        copy(
                            routineList = newRoutineList
                        )
                    }
                }.onFailure {
                    Log.e("aa12", it.toString())
                }
            }
        }
    }

    private suspend fun getExerciseList(
        routineIndex: Int
    ): List<LoadLessonUiState.Routine> {
        val memberId = state.value.selectedMemberId
        val lessonId = state.value.routineList[routineIndex].id

        val response = lessonRepository.getLessonDetail(memberId, lessonId)
        checkNotNull(response) { "response is null" }

        return state.value.routineList.toMutableList().apply {
            this[routineIndex] = state.value.routineList[routineIndex].copy(
                exerciseList = response.toPresentation()
            )
        }.toList()
    }

    private fun getMemberList() {
        viewModelScope.launch {
            runCatching {
                val response = memberRepository.getMemberList()
                checkNotNull(response) { "response is null" }
                val memberList = response.memberList
                memberList
            }.onSuccess {
                setState {
                    copy(
                        memberList = it.map {
                            it.toPresentation()
                        },
                    )
                }
            }.onFailure {
                Log.e("aa12", it.toString())
            }
        }
    }

    fun getLessonList() {
        viewModelScope.launch {
            runCatching {
                currentState {
                    check(selectedMemberId > 0) { "memberId is invaild" }

                    val intendedResponse = lessonRepository.getIntendedLessons(selectedMemberId)
                    val intendedLessonList = intendedResponse?.lessonInfo ?: emptyList()

                    val completedResponse = lessonRepository.getCompletedLessons(selectedMemberId)
                    val completedLessonList = completedResponse?.lessonInfo ?: emptyList()

                    (intendedLessonList + completedLessonList).sortedByDescending {
                        it.lessonsDate
                    }
                }
            }.onSuccess {
                setState {
                    copy(
                        routineList = it.map { lessonInfo ->
                            lessonInfo.toPresentation()
                        }
                    )
                }
            }.onFailure {
                Log.e("aa12", it.toString())
            }
        }
    }

    fun load() {
        setState {
            copy(
                isLoad = true,
            )
        }
    }

    fun setIsLoad(isLoad: Boolean) {
        setState {
            copy(
                isLoad = isLoad,
            )
        }
    }
}
