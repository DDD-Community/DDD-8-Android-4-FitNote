package com.dogandpigs.fitnote.presentation.join

import android.util.Log
import com.dogandpigs.fitnote.data.repository.AccountRepository
import com.dogandpigs.fitnote.data.source.remote.model.JoinRequest
import com.dogandpigs.fitnote.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
internal class JoinViewModel @Inject constructor(
    private val accountRepo: AccountRepository
) : BaseViewModel<JoinUiState>() {
    override fun createInitialState(): JoinUiState = JoinUiState()
    val uiState: MutableStateFlow<JoinUiState> = MutableStateFlow(JoinUiState())

    fun join() = currentState {
        val data = JoinRequest(
            fullName = name,
            email = email,
            firstPassword = firstPassword,
            verifyPassword = firstPassword
        )
        launch {
            accountRepo.join(data)?.run {
                if (user != null) {
                    setState {
                        copy(isJoinSuccess = true)
                    }
                }
            }
        }
    }
}
