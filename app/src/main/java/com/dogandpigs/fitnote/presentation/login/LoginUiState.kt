package com.dogandpigs.fitnote.presentation.login

internal data class LoginUiState(
    val title: String = ""
)

sealed class LoginState() {
    object Success
    object Failed
}