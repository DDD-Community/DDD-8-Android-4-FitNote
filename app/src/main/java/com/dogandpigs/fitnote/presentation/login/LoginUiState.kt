package com.dogandpigs.fitnote.presentation.login

internal data class LoginUiState(
    val title: String = "",
    val email: String = "",
    val password: String = ""
)

sealed class LoginState {
    object Success
    object Failed
}
