package com.dogandpigs.fitnote.presentation.lesson.loadlesson

import com.dogandpigs.fitnote.data.source.remote.model.Info

internal fun Info.toPresentation() : LoadLessonState.Member =
    LoadLessonState.Member(
        id = id,
        name = userName,
    )
