package com.dogandpigs.fitnote.presentation.lesson.loadlesson

import com.dogandpigs.fitnote.data.source.remote.model.LessonResponse
import com.dogandpigs.fitnote.data.source.remote.model.Member
import com.dogandpigs.fitnote.presentation.lesson.addlesson.Routine

data class LoadLessonState(
    val selectedUserId: Int = 0,
    val memberList: List<Member> = listOf(),
    val exerciseList: List<LessonResponse.Description> = listOf()
)
