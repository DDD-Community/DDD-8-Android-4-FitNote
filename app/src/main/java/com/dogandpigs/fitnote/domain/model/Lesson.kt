package com.dogandpigs.fitnote.domain.model

data class Lesson(
    val id: Int,
    val today: String,
    val set: Int,
    var name: String,
    var weight: String,
    var count: Int,
)

