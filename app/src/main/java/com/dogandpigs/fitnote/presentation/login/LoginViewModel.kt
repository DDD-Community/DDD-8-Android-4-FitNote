package com.dogandpigs.fitnote.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.dogandpigs.fitnote.data.repository.AccountRepository
import com.dogandpigs.fitnote.data.source.remote.model.LoginRequest
import com.dogandpigs.fitnote.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class LoginViewModel @Inject constructor(
    private val accountRepo: AccountRepository
) : BaseViewModel() {
    var uiState by mutableStateOf(LoginUiState())
        private set
    
    fun login(email: String, pwd: String) {
        val data = LoginRequest().apply {
            this.email = email
            this.password = pwd
        }
        viewModelScope.launch {
            accountRepo.login(data)?.let { user ->
                uiState = LoginUiState("login")
            } ?: kotlin.run {
                uiState = LoginUiState("failed")
            }
        }
    }
}