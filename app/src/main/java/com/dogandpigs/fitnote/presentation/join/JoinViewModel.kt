package com.dogandpigs.fitnote.presentation.join

import com.dogandpigs.fitnote.data.repository.AccountRepository
import com.dogandpigs.fitnote.data.source.remote.model.JoinRequest
import com.dogandpigs.fitnote.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class JoinViewModel @Inject constructor(
    private val accountRepo: AccountRepository
) : BaseViewModel<JoinUiState>() {
    override fun createInitialState(): JoinUiState = JoinUiState()
    fun join() = currentState {
        val data = JoinRequest(
            name = name,
            email = email,
            firstPassword = password,
            verifyPassword = password
        )
        launch {
            accountRepo.join(data)
        }
    }
}
