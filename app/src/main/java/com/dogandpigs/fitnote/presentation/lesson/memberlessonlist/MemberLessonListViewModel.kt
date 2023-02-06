package com.dogandpigs.fitnote.presentation.lesson.memberlessonlist

import android.util.Base64
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dogandpigs.fitnote.data.repository.LessonRepository
import com.dogandpigs.fitnote.presentation.base.BaseViewModel
import com.dogandpigs.fitnote.presentation.member.memberedit.MemberEditUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class MemberLessonListViewModel @Inject constructor(
    private val lessonRepository: LessonRepository
) : BaseViewModel<MemberLessonListUiState>() {
    val uiState: MutableStateFlow<MemberLessonListUiState> =
        MutableStateFlow(MemberLessonListUiState())
    
    override fun createInitialState(): MemberLessonListUiState = MemberLessonListUiState()
    
    fun initialize(memberId: Int) = currentState {
        Log.d("aa12", "memberId : $memberId")
        setState {
            copy(memberId = memberId)
        }
    }
    
    fun getIntendedLessonList() = currentState {
        viewModelScope.launch {
            lessonRepository.getIntendedLessons(memberId)
        }
    }
}
