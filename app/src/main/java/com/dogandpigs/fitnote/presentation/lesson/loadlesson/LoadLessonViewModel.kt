package com.dogandpigs.fitnote.presentation.lesson.loadlesson

import androidx.lifecycle.viewModelScope
import com.dogandpigs.fitnote.data.repository.LessonRepository
import com.dogandpigs.fitnote.data.repository.MemberRepository
import com.dogandpigs.fitnote.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoadLessonViewModel @Inject constructor(
    private val memberRepository: MemberRepository,
    private val lessonRepository: LessonRepository
) : BaseViewModel<LoadLessonState>() {
    override fun createInitialState(): LoadLessonState = LoadLessonState()
    
    fun initialize() {
        getMemberList()
        getLessonList()
    }
    fun getMemberList() = currentState {
        viewModelScope.launch {
            memberRepository.getMemberList()?.run {
                setState {
                    copy(memberList = this@run.memberList)
                }
            }
        }
    }
    fun getLessonList() = currentState {
        viewModelScope.launch {
            lessonRepository.getCompletedLessons(selectedUserId)?.run {
                setState {
                    copy(exerciseList = this@run.lessonInfo)
                }
            }
        }
    }
}