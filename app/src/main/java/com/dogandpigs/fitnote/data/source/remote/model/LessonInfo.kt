package com.dogandpigs.fitnote.data.source.remote.model

import com.google.gson.annotations.SerializedName

data class LessonInfo(
    @SerializedName("lessons_date")
    val lessonsDate: Int,

    @SerializedName("lessons_name")
    val lessonsName: List<String>,
)
