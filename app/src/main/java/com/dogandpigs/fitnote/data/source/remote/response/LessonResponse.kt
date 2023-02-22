package com.dogandpigs.fitnote.data.source.remote.response

import com.dogandpigs.fitnote.data.source.remote.model.Info
import com.dogandpigs.fitnote.data.source.remote.model.LessonInfo
import com.google.gson.annotations.SerializedName

data class LessonResponse(
    @SerializedName("getMemberInfo")
    val memberInfo: Info,

    @SerializedName("getLessonInfo")
    val lessonInfo: List<LessonInfo>,
)
