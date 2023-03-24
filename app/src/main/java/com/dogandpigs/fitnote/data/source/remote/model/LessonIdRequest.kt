package com.dogandpigs.fitnote.data.source.remote.model

import com.google.gson.annotations.SerializedName

data class LessonIdRequest(
    @SerializedName("lesson_id")
    val lessonId: Int,
)
