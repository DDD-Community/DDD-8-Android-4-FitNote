package com.dogandpigs.fitnote.presentation.setting

import com.dogandpigs.fitnote.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class SettingViewModel @Inject constructor() : BaseViewModel<SettingUiState>() {
    override fun createInitialState(): SettingUiState = SettingUiState()
}
