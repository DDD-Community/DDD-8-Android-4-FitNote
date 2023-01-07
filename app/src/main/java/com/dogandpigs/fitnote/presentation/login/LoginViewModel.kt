package com.dogandpigs.fitnote.presentation.login

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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
    var uiState by mutableStateOf(LoginUiState())
        private set

    fun login() = currentState {
        val data = LoginRequest(email = email, password = password)
        Log.d("Debug", data.toString())
        launch {
            accountRepo.login(data)?.let { user ->
                uiState = LoginUiState("login")
            } ?: kotlin.run {
                uiState = LoginUiState("failed")
            }
        }
    }
}
