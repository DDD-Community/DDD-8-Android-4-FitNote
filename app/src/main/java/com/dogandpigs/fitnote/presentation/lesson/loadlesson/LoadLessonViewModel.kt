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

    fun setSelectedMemberId(memberId: Long) {
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

            }
        }
    }

    fun getLessonList() {
        viewModelScope.launch {
            runCatching {
                currentState {
                    check(selectedMemberId > 0) { "memberId is invaild" }
                    val response = lessonRepository.getIntendedLessons(selectedMemberId)
                    checkNotNull(response) { "response is null" }
                }
            }.onSuccess {
                setState {
                    copy(
                        routineList = it.lessonInfo.map { lessonInfo ->
                            lessonInfo.toPresentation()
                        }
                    )
                }
            }.onFailure {
                Log.e("aa12", it.toString())
            }
        }
    }
}
