package com.dogandpigs.fitnote.domain.model

data class Lesson(
    val id: Int,
    val lessonId: Int = -1,
    val today: String,
    val set: Int,
    var name: String,
    var weight: String,
    var count: Int,
)

