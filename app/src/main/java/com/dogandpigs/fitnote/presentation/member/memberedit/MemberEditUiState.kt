package com.dogandpigs.fitnote.presentation.member.memberedit

internal data class MemberEditUiState(
    val temp: String = "",
    val name: String = "",
    val date: String = "",
    val gender: String = "",
    val height: Double = 0.0,
    val weight: Double = 0.0,
)
