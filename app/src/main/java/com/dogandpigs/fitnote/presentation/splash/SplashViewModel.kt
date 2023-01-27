package com.dogandpigs.fitnote.presentation.splash

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class SplashViewModel @Inject constructor(

) : ViewModel() {
    var uiState by mutableStateOf(SplashUiState())
        private set

    private var checkLoginJob: Job? = null

    init {
        checkLogin()
    }

    // TODO UseCase 필요
    private fun checkLogin() {
        checkLoginJob = viewModelScope.launch {
            delay(2_000L)
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

    fun cancelCheckLogin() {
        if (checkLoginJob?.isActive == true) {
            checkLoginJob?.cancel()
            checkLoginJob = null
        }
    }
}
