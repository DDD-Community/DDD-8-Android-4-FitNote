package com.dogandpigs.fitnote.presentation.lesson.addlesson

import com.google.gson.annotations.SerializedName

data class Routine(
    @SerializedName("id")
    var id: Int = 0,
    var index: Int = 0,
    @SerializedName("name")
    var name: String = "",
    @SerializedName("set")
    var set: Int = 1,
    @SerializedName("weight")
    var weight: Int = 0,
    @SerializedName("count")
    var count: Int = 0,
    @SerializedName("today")
    var today: String = "19000101"
)