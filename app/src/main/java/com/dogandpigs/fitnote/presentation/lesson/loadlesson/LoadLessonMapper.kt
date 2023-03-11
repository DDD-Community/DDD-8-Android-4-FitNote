package com.dogandpigs.fitnote.presentation.lesson.loadlesson

import com.dogandpigs.fitnote.data.source.remote.model.Info
import com.dogandpigs.fitnote.data.source.remote.model.LessonInfo

internal fun Info.toPresentation(): LoadLessonUiState.Member =
    LoadLessonUiState.Member(
        id = id,
        name = userName,
    )

internal fun LessonInfo.toPresentation(): LoadLessonUiState.Routine =
    LoadLessonUiState.Routine(
        id = lessonsDate,
        title = lessonsDate.toTitle(),
        exerciseNameList = lessonsName,
        exerciseList = emptyList(),
        isFold = false,
    )

private fun Int.toTitle() : String =
    this.toString().let {
        "${it.substring(4, 6)}월 ${it.substring(6, 8)}일 운동"
    }
