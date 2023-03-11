package com.dogandpigs.fitnote.presentation.lesson.loadlesson

import com.dogandpigs.fitnote.data.source.remote.model.LessonInfo

internal data class LoadLessonState(
    val selectedUserId: Long = 0L,
    val memberList: List<Member> = emptyList(),
    val exerciseList: List<LessonInfo> = emptyList(),
) {
    data class Member(
        val id: Int,
        val name: String,
    )
}
