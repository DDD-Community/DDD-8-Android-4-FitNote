package com.dogandpigs.fitnote.presentation.member.memberdetail

import com.dogandpigs.fitnote.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class MemberDetailViewModel @Inject constructor() : BaseViewModel<MemberDetailUiState>() {
    override fun createInitialState(): MemberDetailUiState = MemberDetailUiState()
}
