package com.dogandpigs.fitnote.presentation.join

internal data class JoinUiState(
    val name: String = "",
    val email: String = "",
    val firstPassword: String = "",
    val verifyPassword: String = "",
    val isJoinSuccess: Boolean = false,
    val isReadyJoin: Boolean = false,
)
