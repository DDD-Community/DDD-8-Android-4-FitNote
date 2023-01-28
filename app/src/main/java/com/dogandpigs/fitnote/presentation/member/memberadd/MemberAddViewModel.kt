package com.dogandpigs.fitnote.presentation.member.memberadd

import com.dogandpigs.fitnote.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class MemberAddViewModel @Inject constructor() : BaseViewModel<MemberAddUiState>() {
    override fun createInitialState(): MemberAddUiState = MemberAddUiState()
}
