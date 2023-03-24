package com.dogandpigs.fitnote.data.mapper

import com.dogandpigs.fitnote.data.source.remote.model.LessonRequest
import com.dogandpigs.fitnote.domain.model.Lesson

internal fun Lesson.toData(): LessonRequest =
    LessonRequest(
        id = id,
        today = today,
        set = set,
        name = name,
        weight = weight,
        count = count,
    )
