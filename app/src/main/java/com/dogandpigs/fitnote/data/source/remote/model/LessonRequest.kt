package com.dogandpigs.fitnote.data.source.remote.model

import com.google.gson.annotations.SerializedName

data class LessonRequest(
    @SerializedName("id")
    val id: Int,
    @SerializedName("today")
    val today: String,
    @SerializedName("set")
    val set: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("weight")
    var weight: String,
    @SerializedName("count")
    var count: Int,
    @SerializedName("lesson_id")
    val lessonId: Int,
)

