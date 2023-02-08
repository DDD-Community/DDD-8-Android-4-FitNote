package com.dogandpigs.fitnote.presentation.join

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.viewModelScope
import com.dogandpigs.fitnote.data.repository.AccountRepository
import com.dogandpigs.fitnote.data.source.remote.model.JoinRequest
import com.dogandpigs.fitnote.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
internal class JoinViewModel @Inject constructor(
    private val accountRepo: AccountRepository
) : BaseViewModel<JoinUiState>() {
    override fun createInitialState(): com.dogandpigs.fitnote.presentation.join.JoinUiState =
        JoinUiState()

    fun check(
        value: Boolean,
    ) {
        setState {
            copy(
                isReadyJoin = value,
            )
        }
    }

    fun checkEmail(email: String): Boolean =
        !(email.isEmpty() || Patterns.EMAIL_ADDRESS.matcher(email).matches())

    fun checkPassword(password: String): Boolean = !(Pattern.matches(
        "^[a-zA-Z0-9]*\$", password
    ) && password.length > 7 && password.length < 17)

    fun checkCheckPassword(password: String, checkPassword: String): Boolean =
        password != checkPassword

    fun setUiState(joinUiState: JoinUiState) = currentState {
        setState {
            copy(
                name = joinUiState.name,
                email = joinUiState.email,
                firstPassword = joinUiState.firstPassword,
                verifyPassword = joinUiState.verifyPassword,
            )
        }
    }

    fun join() = currentState {
        viewModelScope.launch {
            val data = JoinRequest(
                fullName = name,
                email = email,
                firstPassword = firstPassword,
                verifyPassword = verifyPassword,
            )

            kotlin.runCatching {
                accountRepo.join(data)
            }.onSuccess {
                if (it != null) {
                    setState {
                        copy(isJoinSuccess = true)
                    }
                }
            }.onFailure {
                Log.d("aa12", "$it")
            }
        }
    }

    data class JoinUiState(
        val name: String,
        val email: String,
        val firstPassword: String,
        val verifyPassword: String,
    )
}
