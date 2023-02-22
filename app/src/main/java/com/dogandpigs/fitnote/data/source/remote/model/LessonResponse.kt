package com.dogandpigs.fitnote.data.source.remote.model

import com.google.gson.annotations.SerializedName

data class LessonResponse(
    @SerializedName("getMemberInfo")
    val memberInfo: Info,

    @SerializedName("getLessonInfo")
    val lessonInfo: List<LessonInfo>,
)
