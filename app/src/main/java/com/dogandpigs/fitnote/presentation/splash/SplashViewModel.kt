package com.dogandpigs.fitnote.presentation.splash

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class SplashViewModel @Inject constructor(

) : ViewModel() {
    var uiState by mutableStateOf(SplashUiState())
        private set
}
