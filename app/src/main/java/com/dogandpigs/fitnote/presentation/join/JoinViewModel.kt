package com.dogandpigs.fitnote.presentation.join

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.dogandpigs.fitnote.data.repository.AccountRepository
import com.dogandpigs.fitnote.data.source.remote.model.JoinRequest
import com.dogandpigs.fitnote.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class JoinViewModel @Inject constructor(
    private val accountRepo: AccountRepository
) : BaseViewModel() {
    var uiState by mutableStateOf(JoinUiState())
        private set
    
    fun join(
        name: String,
        email: String,
        pwd: String,
    ) {
        val data = JoinRequest().apply {
            this.name = name
            this.email = email
            firstPassword = pwd
            verifyPassword = pwd
        }
        viewModelScope.launch {
            accountRepo.join(data)
        }
    }
}