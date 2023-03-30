package com.dogandpigs.fitnote.presentation.login

import androidx.lifecycle.viewModelScope
import com.dogandpigs.fitnote.data.repository.AccountRepository
import com.dogandpigs.fitnote.data.source.remote.model.LoginRequest
import com.dogandpigs.fitnote.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class LoginViewModel @Inject constructor(
    private val accountRepo: AccountRepository
) : BaseViewModel<LoginUiState>() {
    override fun createInitialState(): LoginUiState = LoginUiState()

    private val _eventSharedFlow: MutableSharedFlow<LoginEvent> =
        MutableSharedFlow()
    internal val eventSharedFlow: SharedFlow<LoginEvent> = _eventSharedFlow.asSharedFlow()


    fun login() = currentState {
        viewModelScope.launch {
            runCatching {
                val data = LoginRequest(email = email, password = password)
                accountRepo.login(data)
            }.onSuccess {
                setState { copy(loginState = LoginUiState.LoginState.Success) }
            }.onFailure {
                setState { copy(loginState = LoginUiState.LoginState.Failed) }
                _eventSharedFlow.emit(LoginEvent.LoginFail)
            }
        }
    }

    internal fun showToastFindPassword() {
        viewModelScope.launch {
            _eventSharedFlow.emit(LoginEvent.FindPassword)
        }
    }
}
