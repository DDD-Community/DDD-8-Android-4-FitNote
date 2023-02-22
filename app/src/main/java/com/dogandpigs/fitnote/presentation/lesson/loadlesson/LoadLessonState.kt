package com.dogandpigs.fitnote.presentation.lesson.loadlesson

import com.dogandpigs.fitnote.data.source.remote.model.Info
import com.dogandpigs.fitnote.data.source.remote.model.LessonInfo

data class LoadLessonState(
    val selectedUserId: Int = 0,
    val memberList: List<Info> = listOf(),
    val exerciseList: List<LessonInfo> = listOf()
)
