package com.dogandpigs.fitnote.presentation

import com.dogandpigs.fitnote.presentation.base.BaseViewModel
import com.dogandpigs.fitnote.presentation.base.MainEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : BaseViewModel<MainState>() {
    override fun createInitialState(): MainState = MainState

    private val _eventStateFlow: MutableStateFlow<MainEvent> =
        MutableStateFlow(MainEvent.None)
    internal val eventStateFlow: StateFlow<MainEvent> = _eventStateFlow.asStateFlow()

    fun eventCustomToast(customToast: MainEvent.CustomToast) {
        _eventStateFlow.value = customToast
    }
}
