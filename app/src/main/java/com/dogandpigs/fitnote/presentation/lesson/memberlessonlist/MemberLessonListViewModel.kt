package com.dogandpigs.fitnote.presentation.lesson.memberlessonlist

import androidx.lifecycle.viewModelScope
import com.dogandpigs.fitnote.data.repository.LessonRepository
import com.dogandpigs.fitnote.data.repository.MemberRepository
import com.dogandpigs.fitnote.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class MemberLessonListViewModel @Inject constructor(
    private val memberRepository: MemberRepository,
    private val lessonRepository: LessonRepository
) : BaseViewModel<MemberLessonListUiState>() {
    override fun createInitialState(): MemberLessonListUiState = MemberLessonListUiState()

    fun initialize(memberId: Int) = currentState {
        setState {
            copy(memberId = memberId)
        }
        getMemberName()
        getIntendedLessonList()
        getCompletedLessonList()
    }

    private fun getMemberName() = currentState {
        viewModelScope.launch {
            kotlin.runCatching {
                memberRepository.getMemberList()
            }.onSuccess {
                val member = it?.memberList?.first { member ->
                    member.id == memberId
                }

                member?.also {
                    setState {
                        copy(
                            userName = it.userName,
                        )
                    }
                } ?: return@onSuccess // TODO member is NULL
            }
        }
    }

    private fun getIntendedLessonList() = currentState {
        viewModelScope.launch {
            runCatching {
                lessonRepository.getIntendedLessons(memberId)
            }.onSuccess {
                it?.run {
                    val lessonList = mutableListOf<MemberLessonListUiState.Tab.Lesson>()
                    lessonInfo.forEach { lesson ->
                        lessonList.add(
                            MemberLessonListUiState.Tab.Lesson(
                                dateString = lesson.lessonsDate.toString(),
                                exercises = lesson.lessonsName
                            )
                        )
                    }
                    val scheduledTab = MemberLessonListUiState.Tab(
                        tabType = MemberLessonListUiState.Tab.TabType.SCHEDULED,
                        lessons = lessonList,
                    )
                    setState {
                        copy(scheduledLessonTab = scheduledTab)
                    }
                }
            }
        }
    }

    private fun getCompletedLessonList() = currentState {
        viewModelScope.launch {
            runCatching {
                lessonRepository.getCompletedLessons(memberId)
            }.onSuccess {
                it?.run {
                    val lessonList = mutableListOf<MemberLessonListUiState.Tab.Lesson>()
                    lessonInfo.forEach { lesson ->
                        lessonList.add(
                            MemberLessonListUiState.Tab.Lesson(
                                dateString = lesson.lessonsDate.toString(),
                                exercises = lesson.lessonsName
                            )
                        )
                    }
                    val scheduledTab = MemberLessonListUiState.Tab(
                        tabType = MemberLessonListUiState.Tab.TabType.COMPLETED,
                        lessons = lessonList,
                    )
                    setState {
                        copy(completedLessonTab = scheduledTab)
                    }
                }
            }
        }
    }

    fun getMemberInfo() = currentState {
        viewModelScope.launch {
            memberRepository.getMemberInfo()
        }
    }
}
