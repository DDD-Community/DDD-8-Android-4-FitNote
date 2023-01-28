package com.dogandpigs.fitnote.presentation.login

import com.dogandpigs.fitnote.data.repository.AccountRepository
import com.dogandpigs.fitnote.data.source.remote.model.LoginRequest
import com.dogandpigs.fitnote.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class LoginViewModel @Inject constructor(
    private val accountRepo: AccountRepository
) : BaseViewModel<LoginUiState>() {
    override fun createInitialState(): LoginUiState = LoginUiState()

    fun login() = currentState {
        val data = LoginRequest(email = email, password = password)
        launch {
            accountRepo.login(data)?.let { user ->
                setState { copy(loginState = LoginState.Success) }
            } ?: kotlin.run {
                setState { copy(loginState = LoginState.Failed) }
            }
        }
    }
}
