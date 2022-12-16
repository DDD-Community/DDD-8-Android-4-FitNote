package com.dogandpigs.fitnote.presentation.lesson

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class LessonViewModel @Inject constructor(

) : ViewModel() {
    var uiState by mutableStateOf(LessonUiState())
        private set
}
