package com.dogandpigs.fitnote.presentation.adduser

import com.dogandpigs.fitnote.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class AddUserViewModel @Inject constructor(
) : BaseViewModel<AddUserUiState>() {
    override fun createInitialState(): AddUserUiState = AddUserUiState()
}
