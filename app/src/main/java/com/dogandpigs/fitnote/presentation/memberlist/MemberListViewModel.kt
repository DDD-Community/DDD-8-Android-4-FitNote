package com.dogandpigs.fitnote.presentation.memberlist

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
internal class MemberListViewModel @Inject constructor(

) : ViewModel() {
    private val _state: MutableStateFlow<MemberListUiState> = MutableStateFlow(MemberListUiState())
    val state = _state.asStateFlow()

    fun setState(reduce: MemberListUiState.() -> MemberListUiState) {
        val newState = state.value.reduce()
        _state.value = newState
    }
}
