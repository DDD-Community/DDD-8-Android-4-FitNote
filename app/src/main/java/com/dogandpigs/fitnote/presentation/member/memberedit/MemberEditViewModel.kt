package com.dogandpigs.fitnote.presentation.member.memberedit

import com.dogandpigs.fitnote.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class MemberEditViewModel @Inject constructor() : BaseViewModel<MemberEditUiState>() {
    override fun createInitialState(): MemberEditUiState = MemberEditUiState()
}
