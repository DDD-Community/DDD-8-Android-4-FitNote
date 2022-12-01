package com.dogandpigs.fitnote.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.dogandpigs.fitnote.data.repository.AccountRepository
import com.dogandpigs.fitnote.data.source.remote.model.LoginRequestDTO
import com.dogandpigs.fitnote.presentation.base.BaseViewModel
import com.dogandpigs.fitnote.presentation.join.JoinUiState
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
        val data = LoginRequestDTO().apply {
            this.email = email
            this.password = pwd
        }
        viewModelScope.launch {
            accountRepo.login(data)
                .onEach { user ->
                    user ?: kotlin.run {
                        uiState = LoginUiState("login")
                        // TODO: uiState failed?
                        return@run
                    }
                    // TODO: uiState success
                }
        }
    }
}