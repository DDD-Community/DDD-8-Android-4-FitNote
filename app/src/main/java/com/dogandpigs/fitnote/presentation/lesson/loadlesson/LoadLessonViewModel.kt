package com.dogandpigs.fitnote.presentation.lesson.loadlesson

import com.dogandpigs.fitnote.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoadLessonViewModel @Inject constructor() : BaseViewModel<LoadLessonUiState>() {
    override fun createInitialState(): LoadLessonUiState = LoadLessonUiState()
}