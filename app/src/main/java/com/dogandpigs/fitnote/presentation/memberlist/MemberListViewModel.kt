package com.dogandpigs.fitnote.presentation.memberlist

import com.dogandpigs.fitnote.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class MemberListViewModel @Inject constructor() : BaseViewModel<MemberListUiState>() {
    override fun createInitialState(): MemberListUiState = MemberListUiState()
}
