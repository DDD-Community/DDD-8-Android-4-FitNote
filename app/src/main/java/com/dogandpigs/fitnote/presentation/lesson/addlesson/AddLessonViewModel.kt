package com.dogandpigs.fitnote.presentation.lesson.addlesson

import androidx.lifecycle.viewModelScope
import com.dogandpigs.fitnote.core.FormatUtil
import com.dogandpigs.fitnote.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class AddLessonViewModel @Inject constructor() : BaseViewModel() {
    val uiState: MutableStateFlow<AddLessonUiState> = MutableStateFlow(AddLessonUiState())

    init {
        initialize()
    }

    private fun initialize() {
        setCurrentDateString()
    }

    private fun setCurrentDateString() {
        viewModelScope.launch {
            uiState.value = uiState.value.copy(
                dateString = FormatUtil.millToDate(System.currentTimeMillis())
            )
        }
    }
}
