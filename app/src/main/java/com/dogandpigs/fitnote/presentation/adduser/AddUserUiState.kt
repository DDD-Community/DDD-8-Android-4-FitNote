package com.dogandpigs.fitnote.presentation.adduser

data class AddUserUiState(
    val name: String = "",
    val addTimeStamp: Long = 0,
    val gender: String = "",
    val height: Int = 0,
    val weight: Int = 0
)
