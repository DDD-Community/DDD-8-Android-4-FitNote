package com.dogandpigs.fitnote.presentation.lesson.loadlesson

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
) : BaseViewModel<LoadLessonState>() {
    override fun createInitialState(): LoadLessonState = LoadLessonState()

    fun initialize() {
        getMemberList()
//        getLessonList()
    }

    fun getMemberList() {
        viewModelScope.launch {
            runCatching {
                val response = memberRepository.getMemberList()
                val memberList = response?.memberList
                checkNotNull(memberList) { "memberList is null" }
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

//    fun getLessonList() = currentState {
//        viewModelScope.launch {
//            lessonRepository.getCompletedLessons(selectedUserId)?.run {
//                setState {
//                    copy(exerciseList = this@run.lessonInfo)
//                }
//            }
//        }
//    }
}
