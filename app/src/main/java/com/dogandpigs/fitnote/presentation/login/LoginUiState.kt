package com.dogandpigs.fitnote.presentation.login

internal data class LoginUiState(
    val title: String = "",
    val email: String = "",
    val password: String = "",
    val loginState: LoginState = LoginState.Failed
)

sealed class LoginState {
    object Success: LoginState()
    object Failed: LoginState()
}
