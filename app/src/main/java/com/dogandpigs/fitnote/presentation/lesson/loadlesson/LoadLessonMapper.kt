package com.dogandpigs.fitnote.presentation.lesson.loadlesson

import com.dogandpigs.fitnote.data.source.remote.model.Info
import com.dogandpigs.fitnote.data.source.remote.model.LessonDetailResponse
import com.dogandpigs.fitnote.data.source.remote.model.LessonInfo
import com.dogandpigs.fitnote.presentation.util.format

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
        exerciseListVisible = false,
    )

private fun Int.toTitle(): String =
    this.toString().let {
        "${it.substring(4, 6)}월 ${it.substring(6, 8)}일 운동"
    }

internal fun LessonDetailResponse.toPresentation(): List<LoadLessonUiState.Routine.Exercise> =
    this.lessonInfo.flatten().groupBy {
        it.name
    }.let { maps ->
        maps.toList()
            .map { map ->
                val name = map.first
                val set = map.second.map { it.set }.firstOrNull() ?: error("set is null")
                val weight =
                    map.second.map { it.weight }.firstOrNull()?.format()
                        ?: error("weight is null")
                val count = map.second.map { it.count }.firstOrNull() ?: error("count is null")

                LoadLessonUiState.Routine.Exercise(
                    name = name,
                    set = set,
                    weight = weight,
                    count = count,
                )
            }
    }
