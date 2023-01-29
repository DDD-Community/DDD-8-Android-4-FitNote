package com.dogandpigs.fitnote.presentation.member.memberdetail

internal data class MemberDetailUiState(
    val name: String = "",
    val date: String = "",
    val gender: String = "",
    val height: Double = 0.0,
    val weight: Double = 0.0,
)
