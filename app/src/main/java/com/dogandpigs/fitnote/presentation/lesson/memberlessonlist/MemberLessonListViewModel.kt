package com.dogandpigs.fitnote.presentation.lesson.memberlessonlist

import android.util.Log
import androidx.compose.ui.unit.Constraints
import androidx.lifecycle.viewModelScope
import com.dogandpigs.fitnote.core.Constants
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
    }
    
    fun getIntendedLessonList() = currentState {
        viewModelScope.launch {
            lessonRepository.getIntendedLessons(memberId)?.run {
                val lessonList = mutableListOf<MemberLessonListUiState.Tab.Lesson>()
                lessonInfo.forEach { lesson ->
                    Log.d(Constants.TAG_DEBUG, "getIntendedLessonList: ${lesson.lessonsDate} / ${lesson.lessonsName}")
                    lessonList.add(
                        MemberLessonListUiState.Tab.Lesson(
                            dateString = lesson.lessonsDate,
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
    
    fun getMemberInfo() = currentState {
        viewModelScope.launch {
            memberRepository.getMemberInfo()
        }
    }
}
