package com.dogandpigs.fitnote.presentation.lesson.addlesson

data class Routine(
    var index: Int = 0,
    var name: String = "",
    var set: Int = 1,
    var weight: Int = 0,
    var count: Int = 0,
    val today: String = "19000101"
)