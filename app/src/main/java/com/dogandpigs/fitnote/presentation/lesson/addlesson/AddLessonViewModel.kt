package com.dogandpigs.fitnote.presentation.lesson.addlesson

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.dogandpigs.fitnote.core.FormatUtil
import com.dogandpigs.fitnote.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class AddLessonViewModel @Inject constructor() : BaseViewModel() {
    var uiState by mutableStateOf(AddLessonUiState())
        private set
    
    var dateMilliState by mutableStateOf(System.currentTimeMillis())
        private set
    
    var dateStringState by mutableStateOf(FormatUtil.millToDate(dateMilliState))
    
    fun setDate(date: Long) {
        dateMilliState = date
        dateStringState = FormatUtil.millToDate(dateMilliState)
    }
}