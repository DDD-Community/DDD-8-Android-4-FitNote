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
    }

    fun getIntendedLessonList() = currentState {
        viewModelScope.launch {
            lessonRepository.getIntendedLessons(memberId)
        }
    }

    fun getMemberInfo() = currentState {
        viewModelScope.launch {
            memberRepository.getMemberInfo()
        }
    }
}
