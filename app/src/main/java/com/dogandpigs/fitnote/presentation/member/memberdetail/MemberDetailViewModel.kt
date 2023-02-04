package com.dogandpigs.fitnote.presentation.member.memberdetail

import android.util.Log
import com.dogandpigs.fitnote.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class MemberDetailViewModel @Inject constructor() : BaseViewModel<MemberDetailUiState>() {
    override fun createInitialState(): MemberDetailUiState = MemberDetailUiState()

    fun initialize(memberId: Int) {
        Log.d("aa12", "memberId : $memberId")
    }
}
