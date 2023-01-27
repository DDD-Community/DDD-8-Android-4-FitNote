package com.dogandpigs.fitnote.presentation.splash

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class SplashViewModel @Inject constructor(

) : ViewModel() {
    var uiState by mutableStateOf(SplashUiState())
        private set

    init {
        checkLogin()
    }

    // TODO UseCase 필요
    private fun checkLogin() {
        viewModelScope.launch {
            delay(5_000L)
            // 실패 시
            uiState = uiState.copy(
                isShowJoinOrLogin = true
            )

            delay(5_000L)
            // 성공 시
            uiState = uiState.copy(
                isLogin = true
            )
        }
    }
}
